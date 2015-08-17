// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.junit.Test;

/**
 * Test for {@link at.or.vogt.oe1downloader.Main}.
 */
public class MainTest {

    /**
     * Tests for main.
     * @throws Exception if an error occurs
     */
    @Test
    public void testMain() throws Exception {
        final Main dut = new Main();
        dut.run();
    }

}
