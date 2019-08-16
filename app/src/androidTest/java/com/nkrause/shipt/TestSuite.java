package com.nkrause.shipt;

import com.nkrause.shipt.tests.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({BasicTest.class, WinTest.class, DrawTest.class, NewGameTest.class})
public class TestSuite {}