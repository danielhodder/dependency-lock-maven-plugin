import org.apache.commons.io.FileUtils

import static java.nio.charset.StandardCharsets.UTF_8
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.core.CombinableMatcher.both
import static org.hamcrest.core.IsIterableContaining.hasItem
import static org.hamcrest.core.StringEndsWith.endsWith
import static org.hamcrest.core.StringStartsWith.startsWith
import static org.junit.Assert.assertTrue

lockFile = new File(basedir, "dependencies-lock.json")
expectedLockFile = new File(basedir, "expected-dependencies-lock.json")

assertTrue("Lock file missing", lockFile.isFile())
assertTrue("Lock file content not as expected", FileUtils.contentEquals(expectedLockFile, lockFile))

buildLog = FileUtils.readLines(new File(basedir, "build.log"), UTF_8)
assertThat(buildLog, hasItem(both(startsWith("[INFO] Creating ")).and(endsWith("/dependency-lock-maven-plugin/target/its/json-create-lock-file/dependencies-lock.json"))))
