
import static org.junit.Assert.*;
import org.junit.Test;

public class YoungHumanV2Test {
  @Test
  public void weightCloneTest() {
    String warning = "This test creates new Weight(5, 1) and its clone. \n";
    try {
    Weight w1 = new Weight(5, 1);
    Weight w2 = (Weight) w1.clone();
    assertTrue("This test creates new Weight(5, 1) and its clone. \n"
        + "They should equal (equals() should return true), but your return false.\n"
        ,w1.equals(w2));
    assertFalse("This test creates new Weight(5, 1) and its clone. \n"
        + "Your program fails this test because their references are identical, "
        + "you need return a clone copy, not itself.\n", w1 == w2);
    } catch (Exception e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    } catch (Error e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }
  }
  
  @Test
  public void weightCompareToTest1() {
    Weight w1 = new Weight(5, 1);
    Weight w2 = new Weight(5, 1);
    assertEquals("This test creates 2 variables: w1, w2 = new Weight(5, 1). \n"
        + "Their values equal, so w1.compareTo(w2) should be 0, but your is " 
        + w1.compareTo(w2) + ". \n"
        , 0, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest2() {
    Weight w1 = new Weight(5, 1);
    Weight w2 = new Weight(6, 1);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(5, 1); w2 = new Weight(6, 1). \n"
        + "w1 < w2, so w1.compareTo(w2) should be -1, but your is "
        + w1.compareTo(w2) + ". \n", -1, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest3() {
    Weight w1 = new Weight(5, 1);
    Weight w2 = new Weight(5, 2);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(5, 1); w2 = new Weight(5, 2). \n"
        + "w1 < w2, so w1.compareTo(w2) should be -1, but your is "
        + w1.compareTo(w2) + ". \n", -1, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest4() {
    Weight w1 = new Weight(6, 7);
    Weight w2 = new Weight(7, 2);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(6, 7); w2 = new Weight(7, 2). \n"
        + "w1 < w2, so w1.compareTo(w2) should be -1, but your is "
        + w1.compareTo(w2) + ". \n", -1, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest5() {
    Weight w1 = new Weight(6, 1);
    Weight w2 = new Weight(5, 1);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(6, 1); w2 = new Weight(5, 1). \n"
        + "w1 > w2, so w1.compareTo(w2) should be 1, but your is "
        + w1.compareTo(w2) + ". \n", 1, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest6() {
    Weight w1 = new Weight(5, 2);
    Weight w2 = new Weight(5, 1);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(5, 2); w2 = new Weight(5, 1). \n"
        + "w1 > w2, so w1.compareTo(w2) should be 1, but your is "
        + w1.compareTo(w2) + ". \n", 1, w1.compareTo(w2));
  }
  
  @Test
  public void weightCompareToTest7() {
    Weight w1 = new Weight(7, 2);
    Weight w2 = new Weight(6, 7);
    assertEquals("This test creates 2 variables: "
        + "w1 = new Weight(7, 2); w2 = new Weight(6, 7). \n"
        + "w1 > w2, so w1.compareTo(w2) should be 1, but your is "
        + w1.compareTo(w2) + ". \n", 1, w1.compareTo(w2));
  }
  
  @Test
  public void weightGetterTest() {
    Weight weight = new Weight(10, 7);
    String warning = "This test creates new Weight(5, 1). \n";
    assertEquals(warning + " getPounds() should return 10, but your return" 
        + weight.getPounds() + "\n", 10, weight.getPounds());
    assertEquals(warning + " getOunces() should return 7, but your return" 
        + weight.getOunces() + "\n", 7, weight.getOunces());
  }
  
  @Test
  public void weightConstructorTest2() {
    Weight weight = new Weight(-1, -1);
    String warning = "This test creates new Weight(-1, -1), but your lbs and oz "
        + "should not be negative.\nYou need to set them to default value \n";
    assertNotEquals(warning, -1, weight.getPounds());
    assertNotEquals(warning, -1, weight.getOunces());
  }
  
  @Test
  public void weightGetWeightTest() {
    Weight weight = new Weight(12, 8);
    String warning = "This test creates new Weight(12, 8). \n";
    assertEquals(warning + "getWeight() should return 12.5, but your returns" 
        + weight.getWeight() + "\n", 12.5, weight.getWeight(), 0.01);
  }
  
  @Test
  public void weightSetterTest() {
    Weight weight = new Weight(12, 8);
    weight.setWeight(10, 4);
    
    String warning = "This test creates new Weight(12, 8) and calls: "
        + "setWeight(10, 4).\n";
    assertEquals(warning + " lbs should become 10, but your is " 
        + weight.getPounds() + "\n", 10, weight.getPounds());
    assertEquals(warning + " oz should become 4, but your is " 
        + weight.getOunces() + "\n", 4, weight.getOunces());
  }
  
  @Test
  public void weightAddTest() {
    Weight weight = new Weight(10, 4);
    String warning = "This test creates new Weight(10, 4) and "
        + "calls add(1) and add(0, 4).\n";
    weight.add(1);
    weight.add(0, 4);
    assertEquals(warning + " lb should become 11, but your is " 
        + weight.getPounds() + "\n", 11, weight.getPounds());
    assertEquals(warning + " oz should become 8, but your is " 
        + weight.getOunces() + "\n", 8, weight.getOunces());
  }
  
  @Test
  public void weightEqualsTest() {
    Weight w1 = new Weight(10, 4);
    Weight w2 = new Weight(w1);
    String warning = "This test creates 2 variables: "
        + "w1 = new Weight(5, 1) and w2 = new Weight(w2). \n";
    assertTrue(warning + "w1 equals w2, equals() should return true, "
        + "but your returns false\n", w2.equals(w1));
    w1.setWeight(12, 4);
    assertFalse(warning + "Then, w1.setWeight(12, 4), so now w2.equals(w1) "
        + "should return false, but your returns true\n", w2.equals(w1));
  }
  
  @Test
  public void weightEqualsTest2() {
    try {
      Weight w = new Weight(10, 4);
      Date d = new Date(10, 2, 2020);
      String warning = "This test creates 2 variables: "
          + "w = new Weight(10, 4) and d = new Date(10, 2, 2020). \n"
          + "w.equals(d) should throw an Exception in this case.\n";
      assertFalse(warning, w.equals(d));
    }catch (Exception e) {
      
    }catch (Error e) {
      
    }
  }
  
  @Test
  public void weightSetWeightTest() {
    Weight weight = new Weight(1, 1);
    weight.setWeight(-5, -5);
    String warning = "This test creates new Weight(1, 1) and "
        + "calls setWeight(-5, -5), but your lbs and oz "
        + "should not be negative.\nYou need to set them to default value \n";
    assertNotEquals(warning, -5, weight.getPounds());
    assertNotEquals(warning, -5, weight.getOunces());
  }
  
  @Test
  public void weightAddTest2() {
    Weight weight = new Weight(1, 1);
    weight.add(-999, -999);
    String warning = "This test creates new Weight(1, 1) and "
        + "calls weight.add(-999, -999), but your lbs and oz "
        + "should not be negative.\nYou should not add in this case \n";
    assertTrue(warning, weight.getPounds() >= 0);
    assertTrue(warning, weight.getOunces() >= 0);
  }
  
  @Test
  public void dateTest() {
    Date d1 = new Date(5, 1, 2021);
    String warning = "This test creates new Date(5, 1, 2021). \n";
    assertEquals(warning + "day should be 1, but your is " 
        + d1.getDay() + "\n", 1, d1.getDay());
    assertEquals(warning + "month should be 5, but your is " 
        + d1.getMonth() + "\n", 5, d1.getMonth());
    assertEquals(warning + "year should be 2021, but your is " 
        + d1.getYear() + "\n", 2021, d1.getYear());
  }
  
  @Test
  public void dateCompareToTest1() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(5, 1, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021); d2 = new Date(5, 1, 2021). \n"
        + "d1 equals d2, so w1.compareTo(w2) should be 0, but your is "
        + d1.compareTo(d2) + ". \n", 0, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest2() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(6, 1, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021); d2 = new Date(6, 1, 2021). \n"
        + "d1 < d2, so w1.compareTo(w2) should be -1, but your is "
        + d1.compareTo(d2) + ". \n", -1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest3() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(5, 2, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021); d2 = new Date(5, 2, 2021). \n"
        + "d1 < d2, so w1.compareTo(w2) should be -1, but your is "
        + d1.compareTo(d2) + ". \n", -1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest4() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(5, 1, 2022);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021); d2 = new Date(5, 1, 2022). \n"
        + "d1 < d2, so w1.compareTo(w2) should be -1, but your is "
        + d1.compareTo(d2) + ". \n", -1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest5() {
    Date d1 = new Date(7, 8, 2021);
    Date d2 = new Date(8, 7, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(7, 8, 2021); d2 = new Date(8, 7, 2021). \n"
        + "d1 < d2, so w1.compareTo(w2) should be -1, but your is "
        + d1.compareTo(d2) + ". \n", -1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest6() {
    Date d1 = new Date(7, 8, 2021);
    Date d2 = new Date(8, 9, 2020);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(7, 8, 2021); d2 = new Date(8, 9, 2020). \n"
        + "d1 > d2, so w1.compareTo(w2) should be 1, but your is "
        + d1.compareTo(d2) + ". \n", 1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest7() {
    Date d1 = new Date(6, 1, 2021);
    Date d2 = new Date(5, 1, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(6, 1, 2021); d2 = new Date(5, 1, 2021). \n"
        + "d1 > d2, so w1.compareTo(w2) should be 1, but your is "
        + d1.compareTo(d2) + ". \n", 1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest8() {
    Date d1 = new Date(5, 5, 2021);
    Date d2 = new Date(5, 1, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 5, 2021); d2 = new Date(5, 1, 2021). \n"
        + "d1 > d2, so w1.compareTo(w2) should be 1, but your is "
        + d1.compareTo(d2) + ". \n", 1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest9() {
    Date d1 = new Date(5, 1, 2023);
    Date d2 = new Date(5, 1, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2023); d2 = new Date(5, 1, 2021). \n"
        + "d1 > d2, so w1.compareTo(w2) should be 1, but your is "
        + d1.compareTo(d2) + ". \n", 1, d1.compareTo(d2));
  }
  
  @Test
  public void dateCompareToTest10() {
    Date d1 = new Date(4, 1, 2022);
    Date d2 = new Date(5, 5, 2021);
    assertEquals("This test creates 2 variables: "
        + "d1 = new Date(4, 1, 2022); d2 = new Date(5, 5, 2021). \n"
        + "d1 > d2, so w1.compareTo(w2) should be 1, but your is "
        + d1.compareTo(d2) + ". \n", 1, d1.compareTo(d2));
  }
  
  
  @Test
  public void dateCloneTest() {
    String warning = "This test creates new Date(5, 1, 2021) and its clone.";
    try {
      Date d1 = new Date(5, 1, 2021);
      Date d2 = (Date) d1.clone();
      assertTrue("This test creates new Date(5, 1, 2021) and its clone. \n"
          + "They should equal (equals() should return true), but your return false\n"
          ,d1.equals(d2));
      assertFalse("This test creates new date(5, 1, 2021) and its clone. \n"
          + "Your program fails this test because their references are identical,\n"
          + "you need return a clone copy, not itself\n", d1 == d2);
    } catch (Exception e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    } catch (Error e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }
  }
  
  @Test
  public void dateSetterTest() {
    Date d1 = new Date(5, 1, 2021);
    d1.setDay(6);
    d1.setMonth(2);
    d1.setYear(2020);
    String warning = "This test creates new Date(5, 1, 2021) and calls: "
        + "setDay(6), setMonth(2), and setYear(2020).\n";
    assertEquals(warning + " day should become 6, but your is " + d1.getDay() + "\n",
        6, d1.getDay());
    assertEquals(warning + " month should become 2, but your is " + d1.getMonth() + "\n",
        2, d1.getMonth());
    assertEquals(warning + " year should become 2020, but your is " + d1.getYear() + "\n", 
        2020, d1.getYear());
  }
  
  @Test
  public void dateEqualsTest() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(5, 1, 2021);
    assertTrue("This test creates 2 variables: new Date(5, 1, 2021) and compares "
        + "them using equals().\nIt should return true, but your return false\n", 
        d1.equals(d2));
  }
  
  @Test
  public void dateEqualsTest2() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(d1);
    String warning = "This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021) and d2 = new Date(d1). \n";
    assertTrue(warning + "d1 equals d2, equals() should return true, "
        + "but your returns false\n", d2.equals(d1));
    d1.setDay(6);
    assertFalse(warning + "Then, d1.setDay(6), so now d1.equals(d2) "
        + "should return false, but your returns true\n", d1.equals(d2));
    
  }
  
  @Test
  public void dateEqualsTest3() {
    
    try {
      Date d1 = new Date(5, 1, 2021);
      Weight w1 = new Weight(10, 4);
      
      String warning = "This test creates 2 variables: "
          + "w = new Weight(10, 4) and d = new Date(5, 1, 2021). \n"
          + "d.equals(w) should throw an Exception in this case.\n";
      
      assertFalse(warning, d1.equals(w1));
    }catch (Exception e) {
      
    }catch (Error e) {
      
    }
  }
  
  @Test
  public void dateConstructorTest() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(d1);
    d1.setDay(6);
    d1.setMonth(2);
    d1.setYear(2020);
    
    String warning = "This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021) and d2 = new Date(d1). \n";
    assertEquals(warning + " Then, call d1.setDay(6), d2's day should be 1, "
        + "but your is " + d2.getDay() + "\n", 1, d2.getDay());
    assertEquals(warning + " Then, call d1.setMonth(2), d2's month should be 5, "
        + "but your is " + d2.getMonth() + "\n", 5, d2.getMonth());
    assertEquals(warning + " Then, call d1.setYear(2020), d2's year should be 2021, "
        + "but your is " + d2.getYear() + "\n", 2021, d2.getYear());
  }
  
  @Test
  public void dateConstructorTest2() {
    Date d1 = new Date(5, 1, 2021);
    Date d2 = new Date(d1);
    d1.setDay(6);
    String warning = "This test creates 2 variables: "
        + "d1 = new Date(5, 1, 2021) and d2 = new Date(d1).\n"
        + "Then call d1.setDay(6), so d2.equals(d1) should return false, "
        + "but your returns true.\n";
    
    assertFalse(warning, d2.equals(d1));
  }
  
  @Test
  public void dateConstructorTest3() {
    Date d1 = new Date(-1, -1, -1);
    String warning = "This test creates new Date(-1, -1, -1), but your date, month, and year"
        + "should not be negative.\n You need to set them to default value. ";
    assertNotEquals(warning + "day is negative!\n", -1, d1.getDay());
    assertNotEquals(warning + "month is negative!\n", -1, d1.getMonth());
    assertNotEquals(warning + "year is negative!\n", -1, d1.getYear());
  }
  
  @Test
  public void youngHumanConstructorTest1() {
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2021), 
        "Lisa", "Simpson");
    String warning = "This test creates "
        + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\")\n";
      
    assertTrue(warning + ", but your currentWeight is not (45, 3)\n", (y1.getWeight()).equals(new Weight(45, 3)));
    assertTrue(warning + ", but your getName() does not return \"Lisa Simpson\"\n", (y1.getName()).equals("Lisa Simpson"));
    assertTrue(warning + ", but your birthDate is not (3,17,2021)\n", (y1.getBirthDate()).equals(new Date(3,17,2021)));
  }
  
  @Test
  public void youngHumanCloneTest() {
    String warning = "This test creates "
        + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\") "
        + "and its clone.\n";
    try {
      YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2021), 
          "Lisa", "Simpson");
      YoungHuman y2 = (YoungHuman) y1.clone();
      assertTrue("This test creates "
          + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\") "
          + "and its clone.\n"
          + "They should equal (equals() should return true), but your return false\n"
          ,y1.equals(y2));
      assertFalse("This test creates "
          + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\") "
          + "and its clone.\n"
          + "Your program fails this test because their references are identical,\n"
          + "you need return a clone copy, not itself\n", y1 == y2);
    } catch (Exception e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    } catch (Error e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }
  }
  
  @Test
  public void youngHumanSetterTest() {
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2021), 
        "Lisa", "Simpson");
    
    y1.setBirthDate(new Date(2,10,2020));
    y1.setWeight(new Weight(40, 7));
    y1.setName("first", "last");
    
    String warning = "This test creates "
        + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\")"
        + "\nand calls: setBirthDate(new Date(2,10,2020)), setWeight(new Weight(40, 7)), "
        + "and setName(\"first\", \"last\").\n";
    
    assertTrue(warning + " Weight should become (40, 7), but your is " 
        + y1.getWeight() + "\n", (y1.getWeight()).equals(new Weight(40, 7)));
    assertTrue(warning + " name should become \"first last\", but your is " 
        + y1.getName() + "\n", (y1.getName()).equals("first last"));
    //System.out.println(y1.getBirthDate());
    assertTrue(warning + " birthDate should become (2, 10, 2020), but your is " 
        + y1.getBirthDate() + "\n", (y1.getBirthDate()).equals(new Date(2,10,2020)));
  }
  
  @Test
  public void youngHumanCheckUpTest() {
    String warning = "This test creates "
        + "new YoungHuman(Weight(45, 3), Date(3, 17, 2021), \"Lisa\", \"Simpson\")\n"
        + "and call setCheckUp(new Date(7,14,2021).\n";
    try {
      YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2021), 
          "Lisa", "Simpson");
      y1.setCheckUp(new Date(7,14,2021));
      
      assertTrue(warning + " This test is failed because getLastCheckUpDate() "
          + "does not return Date(7,14,2021)\n", 
          (y1.getLastCheckUpDate()).equals(new Date(7,14,2021)));
      
      
      assertTrue(warning + " This test is failed because hasHadCheckUp() "
          + "return false while it should be true\n", y1.hasHadCheckUp());
      y1.unsetCheckUpDate();
      
      assertFalse(warning + " Then call unsetCheckUpDate(). This test is failed "
          + "because hasHadCheckUp() return true while it should be false\n", 
          y1.hasHadCheckUp());
    }catch (Exception e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }catch (Error e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }
  }
  
  @Test
  public void youngHumanToStringTest() {
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
        "Lisa", "Simpson");
    y1.setCheckUp(new Date(7,8,2021));
    String output = "Lisa Simpson, 45 lbs. 3 oz, birth date: "
        + "3/17/2014, last check-up: 7/8/2021";
    System.out.println("\t\t" + y1.toString());
    assertTrue("The correct toString() is: \"" + output + "\"\n, but your is: \"" 
        + y1.toString() + "\".\nThis test will fail if you have any different "
        + "characters,\nso if you have a similar answer, ignore this test case\n", 
        output.equals(y1.toString()));
  }
  
  @Test
  public void youngHumanToStringTest2() {
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
        "John", "Smith");
    String output = "John Smith, 45 lbs. 3 oz, birth date: "
        + "3/17/2014, has not had a check-up";
    System.out.println("\t\t" + y1.toString());
    assertTrue("The correct toString() is: \"" + output + "\"\n, but your is: \"" 
        + y1.toString() + "\".\nThis test will fail if you have any different "
        + "characters,\nso if you have a similar answer, ignore this test case\n", 
        output.equals((y1.toString())));
  }
  
  @Test
  public void youngHumanEqualsTest3() {
    String warning = "This test creates 2 variables: "
        + "y1, y2 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), "
        + "\"Lisa\", \"Simpson\") and calls y1.equals(y2). \n";
    try {
      YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
          "Lisa", "Simpson");
      YoungHuman y2 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
          "Lisa", "Simpson");
      boolean result = y1.equals(y2);
      assertTrue(warning + "It should return true, but your returns false\n", 
          result);
      
    }catch (Exception e) {
      assertFalse(warning + "It crashes with a " + e.toString() ,true);
    }catch (Error e) {
      assertFalse(warning + "It crashes with a " + e.toString(),true);
    }
  }
  
  @Test
  public void youngHumanEqualsTest4() {
    String warning = "This test creates 2 variables: "
        + "y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), "
        + "\"Lisa\", \"Simpson\") and y2 = new YoungHuman(y1).\n"
        + "Then, call y1.setBirthDate(new Date(4,17,2014)).\n"
        + "y1.equals(y2) should return false, but your return true\n";
    
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
        "Lisa", "Simpson");
    
    YoungHuman y2 = new YoungHuman(y1);
    y1.setBirthDate(new Date(4,17,2014));
    
    assertFalse(warning, y1.equals(y2));
  }
  
  @Test
  public void youngHumanPrivacyLeakTest() {
    YoungHuman y1 = new YoungHuman(new Weight(45, 3), new Date(3,17,2014), 
        "Lisa", "Simpson");
    
    String warning = "This test creates y1 = new YoungHuman(new Weight(45, 3), "
        + "new Date(3,17,2014), \"Lisa\", \"Simpson\").\n";
    
    Weight w1 = y1.getWeight();
    y1.setWeight(new Weight(40, 7));
    
    assertTrue(warning + "Then call Weight w1 = y1.getWeight(); "
        + "y1.setWeight(new Weight(40, 7));.\n Your w1 should be (45, 3), "
        + "but it is: " + w1, w1.equals(new Weight(45, 3)));
    
    Date d1 = y1.getBirthDate();
    y1.setBirthDate(new Date(2,10,2020));
    
    assertTrue(warning + "Then call Date d1 = y1.getBirthDate(); "
        + "y1.setBirthDate(new Date(2,10,2020));.\n Your d1 should be (3,17,2014),"
        + " but it is now: " + d1, d1.equals(new Date(3,17,2014)));
  }
}
