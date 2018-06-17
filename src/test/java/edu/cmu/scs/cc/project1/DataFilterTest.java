package edu.cmu.scs.cc.project1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Usage:
 * mvn test
 *
 * You should pass all the provided test cases before you make any submission.
 *
 * Feel free to add more test cases.
 */
class DataFilterTest {

    @Test
    void getColumns() {
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0"),
                new String[]{"en", "Carnegie_Mellon_University", "34", "0"}));
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en User%3AK6ka 34 0"),
                new String[]{"en", "User:K6ka", "34", "0"}));
    }

    @Test
    void checkDataLength() {
        assertTrue(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University%2034 34 0")));
    }

    @Test
    void checkDomain() {
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en.m Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDomain(
                DataFilter.getColumns("en.M Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDomain(
                DataFilter.getColumns("fr Carnegie_Mellon_University 34 0")));
    }

    @Test
    void checkSpecialPage() {
        assertTrue(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Main_Page 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en - 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en %2D 34 0")));
    }

    @Test
    void checkPrefix() {
        assertTrue(DataFilter.checkPrefix(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User:K6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en USer:K6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3AK6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3aK6ka 34 0")));
    }

    @Test
    void checkSuffix() {
        assertTrue(DataFilter.checkSuffix(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en Carnegie_Mellon_University.png 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en Carnegie_Mellon_University.PNG 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en Carnegie_Mellon_University.tXt 34 0")));
    }

    @Test
    void checkFirstLetter() {
        assertFalse(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en carnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en 1arnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en %0045arnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en.m Ï‹ÎµÏƒÏ€Î±Î¼Ï‚ÏƒÎ½ 476832 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en.m Ñ‚Ñ†Ð¹Ð¶Ñ‡ 374015 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en Ï\u0081Î¶Ï…Ï‹ 361954 0")));
    }

    @Test
    void test() {
        System.out.println(Character.isLowerCase('Ï'));
    }

    @Test
    void checkAllRules() {
        assertTrue(DataFilter.checkAllRules(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkAllRules(
                DataFilter.getColumns("en carnegie 34 0")));
    }
}