package br.edu.ifsp.domain.usecases;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages({"br.edu.ifsp.domain.usecases"})
@SuiteDisplayName("All unit tests")
@IncludeTags({"UnitTest"})
public class UnitTests {
}
