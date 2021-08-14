#!/usr/bin/env python3

import json
import os
import sys
from datetime import datetime
from contextlib import redirect_stderr, redirect_stdout
import subprocess
from junitparser import JUnitXml
from glob import glob

# ---------------------------- FUNCTIONS -------------------------------

# function to echo command to console and execute on system 
# parameters:
# 	cmd = unix command to run 
def echoAndRun(cmd):
    print("===================*****************===================")
    print(cmd)
    os.system(cmd)


# function to parse test names from the JUnit file
# returns 
#	tests = a dictionary of {testname: junit test object, ...}
def getTests():
	tests = {}
	for file in glob("target/surefire-reports/TEST-*.xml"):
		results = JUnitXml.fromfile(file)
		tests.update({"{}.{}".format(test.classname, test.name): test for test in results})
	return tests

# function to print the summary of the JUnit test results 
# parameters:
#   testClassName = the junit class name responsible for the test
#	file = report file that is being written to (since it's needed as an arg to printAndWrite)
#	line3 = line in the surefire report containing all this info that will be extracted 
# returns:
#	totalTests = number of tests in this test set 
#	testsPassed = number of successful passes (helps determine if score is 100% or not later)	
def printSummary(testClassName, file, line3):
	line3 = line3.replace(',','') #strip commas out 
	testResults = line3.split() #then split on spaces 
	
	#now assign into test summary variables based on position in testResults list 
	totalTests = int(testResults[2])
	testsFailed = int(testResults[4])
	testErrors = int(testResults[6])
	testsSkipped = int(testResults[8])
	timeElapsed = float(testResults[11])
	testsPassed = totalTests - testsFailed - testErrors - testsSkipped

	printAndWrite(file, "{} TEST SUMMARY".format(testClassName).center(79))
	printAndWrite(file, "===============================================================================")
	printAndWrite(file, "Total Tests:     " + str(totalTests))
	printAndWrite(file, "Tests Passed:    " + str(testsPassed))
	printAndWrite(file, "Tests Failed:    " + str(testsFailed))
	printAndWrite(file, "Test Errors:     " + str(testErrors))
	printAndWrite(file, "Tests Skipped:   " +  str(testsSkipped))
	printAndWrite(file, "Time Elapsed:    " + str(timeElapsed) + "s")
	return totalTests, testsPassed



# printing function for hints for students on failed tests
# parameters:
#	file = report file that we're printing to (needed as param for printAndWrite)
def printHints(file):
	printAndWrite(file, "\n******************************************************************************")
	printAndWrite(file, "*                        HINT FOR FAILED TEST CASES!                         *")
	printAndWrite(file, "* Check your output carefully. Any extra characters like a space can cause   * ")
	printAndWrite(file, "* a test case failure. There should be 1 space after \':\' and you should      * ")
	printAndWrite(file, "* print out new line using System.out.println or \\n at the end of each line. *")
	printAndWrite(file, "******************************************************************************\n")
					
# function to read in point values for each test from given file or using default 
# parameters: 
#	scoresfile = file given as commandline arg or "none" if no file was given 
#		expecting format in file to be "testNumber' 'pointValue"
#	tests = the tests of this test set
# 	defaultPoints = default point value hardcoded at beginning of main
# returns: 
#	points = a dictonary maps name of the test to number of points {test name: point value}
def getScores(scoresfile, tests, defaultPoints):
	points = {}
	for name, result in tests.items():
		points[name] = defaultPoints
	if scoresfile != "none":
		if os.path.isfile(scoresfile):
			with open(scoresfile) as pointsfile:
				pointlines = pointsfile.readline()
				while(pointlines): # while not reaching EOF
					pointlines = pointlines.strip('\n')
					pointsplit = pointlines.split(" ")
					testName = pointsplit[0]
					points[testName] = int(pointsplit[1])
					pointlines = pointsfile.readline()
		else: # issue with scores file, use default points 
			print("Error reading scores file. Default points per test used. Default = ", defaultPoints)
	return points

# function to print start time of the test at top of report 
# parameters:
#	file = report file that we're printing to (needed as param for printAndWrite)
def printDateTimeStart(file):
	# datetime object containing current date and time
    now = datetime.now()
    # dd/mm/YY H:M:S
    dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
    printAndWrite(file, "******************************************************************************")
    printAndWrite(file, "Report generated on " + dt_string)
    printAndWrite(file, "******************************************************************************")

# function to print end time of the test at bottom of report 
# parameters:
#	file = report file that we're printing to (needed as param for printAndWrite)
def printDateTimeEnd(file):
	now = datetime.now()
	# dd/mm/YY H:M:S
	dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
	printAndWrite(file, "******************************************************************************")
	printAndWrite(file, "Report ended on " + dt_string)
	printAndWrite(file, "******************************************************************************")

# function to print ending to report file. It prints end time, closes file, and quits program 
# parameters:
#	file = report file that we're printing to 
# 			(needed as param for printAndWrite) and closing before quitting 
def printDateCloseQuit(file):
	printDateTimeEnd(file)
	file.close()
	quit()

# function to write the test report based on surefire report created by Maven for JUnit tests 
# parameters: 
#	reportFile = file object that the report is writing to 
#	resultFile = relative file path to the surefire report file
#	scoresList = list of point values for each JUnit test 
#	tests = Dictionary of the test name mapped to junit test class
#	defaultPoints = default point value set in main 
# returns: no return necessary 
def writeTestReport(reportFile, resultFile, scoresList, tests, defaultPoints):
	# Get test class name from resultFile
	testClassName = resultFile.split("/")[-1].split(".")[0]

	# Stores the tests map corresponding to this particular test suite
	classTests = {}
	maxScore = 0

	# Filters tests and obtain max score
	for name, test in tests.items():
		if name.startswith("{}.".format(testClassName)):
			classTests[name] = test
			maxScore += scoresList[name]

	# first read in surefire test report created by Maven 
	with open(resultFile) as inFile:
		lines = inFile.readlines()
		
		# print first 3 lines 
		for i in range(0,3):
			printAndWrite(reportFile, lines[i].strip())
		#test summary data is extracted from line 3 
		line3 = lines[3] 
		
		#send line 3 data to function to print test summary chart, returns number of tests 
		totalTests, testsPassed = printSummary(testClassName, reportFile, line3)
		
		#create array of zeros for error messages 
		errorMessages = {}
		
		# list to hold student's scores by test, starts off with full points 
		testPoints = {}

		for name, test in classTests.items():
			result = test.result
			if not len(result):
				testPoints[name] = scoresList[name]
				continue
			result = result[0].text
			em = "------------------------------------------------------------------------------\n"
			em = em + " Test {}.{} has FAILED \n".format(test.classname, test.name)
			em = em + "------------------------------------------------------------------------------\n"
			em = em + "Time elapsed: {} s <<< FAILURE!\n".format(test.time)
			em = em + result + "\n"
			testPoints[name] = 0
			errorMessages[name] = em
		#done parsing surefire report file 
		inFile.close()

		# scoring the results 
		totalScore = sum(testPoints.values()) # sum of student's passed test points 
		perc = (totalScore/maxScore) * 100 # calculate percentage
		
		# print total score at the bottom of the test summary 
		printAndWrite(reportFile, "------------------------------------------------------------------------------")
		printAndWrite(reportFile, "TOTAL SCORE FOR TEST {}: {}/{} = {}%".format(testClassName, totalScore, maxScore, "{:.2f}".format(perc)))
		printAndWrite(reportFile, "==============================================================================")
		
		# if the program passed all tests, write reminder to check 
		if(perc == 100):
			printAndWrite(reportFile, "\nThe code has passed all tests! \nCheck your checkstyle and misspell results for style feedback")
		else:
			# prior to printing test result details, print hints and checkstyle misspell reminder 
			printHints(reportFile)
			printAndWrite(reportFile, "\nTest(s) did not pass. See details below.")
			printAndWrite(reportFile, "Don't forget to check your Checkstyle and misspell results for style feedback")
		
		# now go through collected error messages for each test and print pass or fail messages and error messages 
		printAndWrite(reportFile, "\nTEST RESULT DETAILS:")

		for name, test in classTests.items():
			if not errorMessages.get(name):
				tp = "------------------------------------------------------------------------------" + "\n"
				tp = tp + " Test {} has PASSED\n".format(name)
				tp = tp + "------------------------------------------------------------------------------"
				printAndWrite(reportFile, tp) # now print test passed message 
			else:
				printAndWrite(reportFile, errorMessages[name])
			printAndWrite(reportFile, "Score: " + str(testPoints[name]) + "/" + str(scoresList[name])) 
		
		# also print total score at the bottom in case the test result details are long 
		printAndWrite(reportFile, "==============================================================================")
		printAndWrite(reportFile, "TOTAL SCORE FOR TEST {}: ".format(testClassName) + str(totalScore) + "/" + str(maxScore) + " = {:.2f}".format(perc) + "%")
		printAndWrite(reportFile, "==============================================================================")   

# Run a command given the command prefix and file pattern
# parameters:
#   prefix = the command name
#   pattern = the files pattern to run the command on
def runCommand(prefix, pattern):
	for file in glob(pattern):
		echoAndRun("{} {}".format(prefix, file))

# Retunrs a list of files that have been compiled and failed compilation
def getCompilation():
	classFiles = glob("src/main/java/*.class")
	javaFiles = glob("src/main/java/*.java")
	unCompiled = []
	compiled = []
	for jFile in javaFiles:
		name = jFile.split("/")[-1].split(".")[0]
		if "src/main/java/" + name + ".class" not in classFiles:
			unCompiled.append(name)
		else:
			compiled.append(name)
	return (compiled, unCompiled)

# function to run the code with Maven, test for compilation, and write test report 
# parameters:
#	scoresfile = relative file path to the file containing individual test points 
#	defaultPoints = default point value for each test assigned in main 
def runWithMaven(scoresfile, defaultPoints):
	if not os.path.exists("out/report.txt"):
		os.makedirs("out")
	# now writing new test report file in out/ folder 
	with open("out/report.txt", "w+") as reportFile:
		printDateTimeStart(reportFile)
	
		# now try to compile and test code with Maven 
		runCommand("rm", "src/main/java/*.class")
		echoAndRun("mvn clean compile test --quiet --no-transfer-progress --batch-mode --fail-never")
		os.makedirs("out", exist_ok=True)
		# also echo javac command to test compilation 
		echoAndRun("javac src/main/java/*.java")
		compiled, uncompiled = getCompilation()
		if len(uncompiled) == 0:
			printAndWrite(reportFile, "Found [{}].class file. Program compiled successfully. Tests can proceed:".format(",".join(compiled)))
		else:
			printAndWrite(reportFile, "\n[{}].java failed to compile. No further tests can be run.\n".format(",".join(uncompiled)))
			reportFile.write("==============================================================================\n")
			# get the javac output
			print("=============================================================================\n")
			# get error message from subprocess and write to report 
			for javafile in uncompiled:
				proc = subprocess.Popen(
					["javac src/main/java/" + javafile + ".java"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
				(out, err) = proc.communicate()
				if out:
					printAndWrite(reportFile, "Program output: ")
					printAndWrite(reportFile, out.decode())
				if err:
					printAndWrite(reportFile, "Error messages: \n")
					printAndWrite(reportFile, err.decode())
			printDateCloseQuit(reportFile)
	
		# get test names from JUnit file located at namesFile path
		tests = getTests()
			
		# now get points per test data from scoresfile 
		scoresList = getScores(scoresfile, tests, defaultPoints)
	
		for resultFile in glob("target/surefire-reports/*.txt"):
			writeTestReport(reportFile, resultFile, scoresList, tests, defaultPoints)
				
		# print ending timestamp, close fp, and quit program  
		printDateCloseQuit(reportFile)

# function to both print to console and write to file
# parameters:
# 	file = file object to be written to 
#	string = string to be written (newline gets added)
def printAndWrite(file, string):
	print(string)
	file.write(string + "\n")

# ------------------------------------ SCRIPT MAIN -----------------------------------
def main():
	defaultPoints = 5 # here is where to assign default points per test 
	print("Running testreport.py script") 

	# copy code in the workflow YML file, not here since it's too specific to assignments 

	# handle script arguments
	n = len(sys.argv)
	if(n < 2):
		print("Java file test name provided. Scores file not provided.")
		print("Will be scored with default test value of 5 points per test")
		scoresfile = "none"
	elif(n == 2):
		print("Correct number of arguments passed, proceeding with tests")
		#print(sys.argv)
		scoresfile = sys.argv[1]

	runWithMaven(scoresfile, defaultPoints)

if __name__ == "__main__":
	main()
