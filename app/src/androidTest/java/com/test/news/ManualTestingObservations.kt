package com.test.news

/*
 <=====================================================================================================================>
                                  Manual Testing observations
 <=====================================================================================================================>

 1. Summary: News feed content does not match with navigated web content
 Bug Type: Functional bug
 Priority: High
 Severity: High
 Description: Origin content(Image) from the news feed does not match with the content on the external browser post navigation for some of the image widgets.
 Replication Steps:
                     1. Login to News
                     2. Randomly click on Image widgets on news feed, check the content in browser navigation
 Possible Root cause: Response object from API response is not parsed or handled properly at UI
                      or
                      API response data contains wrong navigation urls

 <=====================================================================================================================>

 2. Summary: Duplicate content(image widgets) displayed on news feed.
 Bug Type: Functional bug
 Priority: High
 Severity: High
 Description: Image widgets are duplicated on news feed
 Replication Steps:
                     1. Login to News
                     2. scroll through news feed
 Possible Root cause: Response object from API response is not parsed or handled properly at UI
                      or
                      API response data contains duplicate information

 <=====================================================================================================================>

 3. Summary: Widgets in slide view having padding and spacing issues
 Bug Type: Compatibility Bug (Non functional)
 Priority: Medium
 Severity: Low
 Description:  Few widgets in slide view (horizontal scroll) are having padding and spacing Issues, some of them are intermittent.
 Replication Steps:
                     1. Login to News
                     2. scroll through back and forth on slide news feed to replicate the padding issues
 Possible Root cause: UI design implementation and Image sizing

 <=====================================================================================================================>
 Usability Bug: Medium Priority
 -> No visible hints to user, suggesting that some of them are horizontally scrollable views.
 3. Summary: No visible hits to user on horizontal scroll bars in news feed.
 Bug Type: Usability Bug (Non functional)
 Priority: Medium
 Severity: Low
 Description: On the news feed, no hints provided to the user suggesting that some of them are scrollable horizontally to find additional widgets.

 <=====================================================================================================================>

 From the above observations
 User story 1 (1 - As a user I want to log in to the app) is successfully tested can be moved to done.
 User story 2 (2 - As a user I want to see my news) can't be moved due to above high priority bugs which needs fixing.
  */
