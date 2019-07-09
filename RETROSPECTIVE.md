# What went well

* Got alotta shit done (board almost totally done — only 2 issues carried over, way more hours spent in velocity diagram)
* Communication on slack
* Meetings well attended, structured, got things done
* Scoping Down
* Testing was better
* Getting things done earlier (Starting earlier)



## Part of project in last iteration that's not been succesfull

* Blocking (empty backlog for things in review / blocked — limbo state of not getting stuff done)
  * Delays in review process - things sitting in "ready for review" — 
* Missing cohesive vision of the project —.both  features and down to specific classes/implementations
* Merge conflicts creating problems, taking time to fix them
* Remembering to update architecture and time spent after completing an issue

### How it can be improved this iteration

* For things blocking / waiting for review
  * Check the gitlab and slack more frequently, Once you see something, get it out of the way right away, Put a message in slack saying hey, I've reviewed a few other things, someone else mind reviewing that thing by that other dude?
  * Not have issues that block eachother — arrange or plan issues in a way that the work can be contained
* Missing cohesion
  * Talking / keeping up the communication — Not just in meetings, if you ever have a question about implementation, bring it up in a public slack channel, start a thread about it, we'll discuss
* Merge conflicts (on merging `develop` **INTO** a branch)
  * Make sure we know how to do them — Use some IDE tool that shows you the `develop>>>>` `this<<<<<<`, and "use mine / use develop" buttons. Don't use plain text editor. **BE CAREFUL WHEN DOING THIS, THINK ABOUT IT, sometimes you need to take code from both sections!**
    * If unsure about which one to use, put up a quick slack message
  * TEST AFTER YOU FIX THE MERGE CONFLICTS! Run all tests, Play the app, make sure all the new develop features still work.
  * Make smaller commits. Make small merge requests. **MERGE DEVELOP INTO YOUR BRANCH OFTEN!**
* Remembering to Update Architecture
  * just do it. please remember.
  * Watch for this when you're reviewing, make sure they have the architecture.md update, (friendly reminder)

### How this improvement will be evaluated at end of iteration

* Blocking
  * We can see time from when PR was created to time PR was merged, and nothings over 1-2 days
  * Little to no issues that have a "blocked" tag
* Cohesion — It's a little late to improve this…. We'll know if it was improved… Survey the team.
* Merge conflicts — We don't have 3 hours where we're racing to fix this before the release due date
* If we're ready to update the architecture diagram and nothing's missing, or we're looking through all the issues before submitting, and they all have time spent

