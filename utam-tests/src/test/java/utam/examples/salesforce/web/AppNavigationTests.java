/*
 * Copyright (c) 2021, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: MIT
 * For full license text, see the LICENSE file in the repo root
 * or https://opensource.org/licenses/MIT
 */
package utam.examples.salesforce.web;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utam.force.pageobjects.ListViewManagerHeader;
import utam.global.pageobjects.AppNavBar;
import utam.global.pageobjects.ConsoleObjectHome;
import utam.global.pageobjects.RecordActionWrapper;
import utam.navex.pageobjects.DesktopLayoutContainer;
import utam.utils.salesforce.RecordType;
import utam.utils.salesforce.TestEnvironment;

/**
 * IMPORTANT: Page objects and tests for Salesforce UI are compatible with the application version
 * mentioned in published page objects Test environment is private SF sandbox, not available for
 * external users and has DEFAULT org setup
 *
 * @author Salesforce
 * @since Dec 2021
 */
public class AppNavigationTests extends SalesforceWebTestBase {

  private final TestEnvironment testEnvironment = getTestEnvironment("sandbox");

  @BeforeTest
  public void setup() {
    setupChrome();
    login(testEnvironment, "home");
  }

  /**
   * navigate to object home via URL and click New
   *
   * @param recordType record type affects navigation url
   */
  private void openRecordModal(RecordType recordType) {

    log("Navigate to an Object Home for " + recordType.name());
    getDriver().get(recordType.getObjectHomeUrl(testEnvironment.getRedirectUrl()));

    log("Load Accounts Object Home page");
    ConsoleObjectHome objectHome = from(ConsoleObjectHome.class);
    ListViewManagerHeader listViewHeader = objectHome.getListView().getHeader();

    log("List view header: click button 'New'");
    listViewHeader.waitForAction("New").click();

    log("Load Record Form Modal");
    RecordActionWrapper recordFormModal = from(RecordActionWrapper.class);
    Assert.assertTrue(recordFormModal.isPresent(), "record creation modal did not appear");
  }

  @Test
  public void testNavigateToNanBarItem() {
    getDriver().get(testEnvironment.getRedirectUrl());
    log("Load Desktop layout container");
    DesktopLayoutContainer layoutContainer = from(DesktopLayoutContainer.class);

    log("Navigate to nav bar item accounts");
    AppNavBar navBar= layoutContainer.getAppNav().getAppNavBar();
    navBar.getNavItem("Account").clickAndWaitForUrl("Account");
  }

  @Test
  public void testNavigateToNanBarOverflowItem() {
    getDriver().get(testEnvironment.getRedirectUrl());
    log("Load Desktop layout container");
    DesktopLayoutContainer layoutContainer = from(DesktopLayoutContainer.class);

    AppNavBar navBar= layoutContainer.getAppNav().getAppNavBar();
    log("Navigate to overflow menu item");
    navBar.getShowMoreMenuButton().expand();
//    menu item with name 'Forecasts' should present in overflow items
    navBar.getShowMoreMenuButton().getMenuItemByText("Forecasts").clickAndWaitForUrl("forecasting");
  }

  @AfterTest
  public final void tearDown() {
    quitDriver();
  }
}