# Math Quiz Generator v.2

This app generates a miniature math quiz based on what the user wants. It asks the user what difficulty they would like the problems to be, as well as the operation they want the quiz to be on and how many questions they want. After the quiz is over, the app tells you how many questions you got correct out of the total questions and sends you back to the home screen.

## Functionality 

The following **required** functionality is completed:

* [✅] User sees radio buttons to choose difficulty and operation
* [✅] User can choose the number of questions they'd like to answer
* [✅] User answers are checked accurately
* [✅] After all questions are done, the user is sent back to home screen with the TextView changing based on percentage answered correctly

The following **extensions** are implemented:

* N/A

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Video Walkthrough](https://github.com/egoel5/C323_Project3/blob/project4/Project%204%20Video%20Walkthrough.gif)

GIF created with [Adobe Express]().

## Notes

One challenge I encountered while working on this project was that my arguments would be null when passed back to the main screen fragment after all questions were answered. The fix was setting defaultValues for all my arguments in the nav_graph which allowed my Fragments to successfully pass arguments back to main screen.
## License

    Copyright [2023] [Eshan Goel]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
