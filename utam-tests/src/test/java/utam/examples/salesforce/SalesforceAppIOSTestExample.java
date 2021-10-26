/*
 * Copyright (c) 2021, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: MIT
 * For full license text, see the LICENSE file in the repo root
 * or https://opensource.org/licenses/MIT
 */
package utam.examples.salesforce;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utam.base.SalesforceMobileTestBase;

public class SalesforceAppIOSTestExample extends SalesforceMobileTestBase {

  @BeforeTest
  public void setUp() {
    setupIOS();
  }

  @Test
  public void testSetDataConnection() {
    // Setup test org data connection
    setDataConnection();
    // from(Login.class, "Login | Salesforce");
  }
}
