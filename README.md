Iteration 1


User story: As a language learner, I want the to be able to translate words to and from my language, so that I can remember words better.
Example: When the user has chosen their languages, they can choose between two modes to play. One mode for translating words from the foreign language to their native language
and one mode for translating from their native language to the foreign language.
Status: Implemented.


User story: As a language learner, I want to be able to peek at the correct translation of a word, so that I can try to remember it in filling out the puzzle.
Example: When a user selects a Sudoku cell that is part of the pre-filled configuration of the puzzle, the translation of that word is momentarily displayed.
Status: Not implemented.


User story: As a language learner, I want to be able to choose from different subjects of words to study, such as studying colors, numbers, etc.
Example: When a user has finished selecting the mode and languages, they will be prompted to select which type of words they would like to study, for example. colors, numbers, etc. The
sudoku puzzle will then use these set of words.
Status: Implemented.


Iteration 2


User story: As a vocabulary learner practicing at home, I want to use my tablet for Sudoku vocabulary practice, so that the words will be conveniently displayed in larger, easier to
read fonts.
Example: When the user is using a tablet, the sudoku grid will be configured to take up the maximum space by using a layout made specifically for a tablet.
Status: Implemented.


User story: As a vocabulary learner taking the bus, I want to use my phone in landscape mode for Sudoku vocabulary practice, so that longer words are displayed in a larger font than
standard mode.
Example: When the user rotates the screen, the sudoku grid cells will become longer to accommodate longer words for improved readability.
Status: Implemented.


User story: As a teacher, I want to specify a list of word pairs for my students to practice this week.
Example: When a teacher wishes to provide a word list for practice with the Sudoku app, the teacher may send the pairs of words to practice as a CSV file.
Example: When a student selects import word list, the system will parse and store the given word list and update the word list submenu with the name of the word list.
Example: When a student selects a word list from the word list submenu, puzzles will be generated using word pairs from that list.
Status: Implemented.


User story: As a student who wants to practice my understanding of spoken words in the language that I am learning, I want a listening comprehension mode. In this mode, numbers will
appear in the prefilled cells. When I press the number, the corresponding word in the language that I am learning will be read out to me. I can then test my listening comprehension by
entering the English translation of the word into an appropriate puzzle cell.
Example: The user will be able to select whether they would like to play in Listening Comprehension Mode, similar to how they chose between mode 1 and mode 2 earlier.
Status: Implemented.


Iteration 3
User Stories:
1.As a teachers and students of elementary school and secondary school, we want a simple sudoku problem, so that we can do some simple practices.
Scenario 1:
Given: There are selections of different sizes of sudoku game.
When: Users can select 4x4 sizes of easier game.
Then:  The sudoku grid will change to 4x4 format.
Scenario 2:
Given: There are selections of different sizes of sudoku game.
When: Users can select 6x6 sizes of easier game.
Then:  The sudoku grid will change to 6x6 format.
2.As a high level language learner, we want a hard sudoku problem, so that we can do some difficult practices.
Scenario 1:
Given: There are selections of different sizes of sudoku game.
When: Users can select 12x12 sizes of hard game on the tablet.
Then:  The sudoku grid will change to 12x12 format on the tablet.
Scenario 2:
Given: There are selections of different sizes of sudoku game.
When: Users can select 12x12 sizes of hard game on the cell phone.
Then:  The sudoku grid will change to 12x12 format, but it will recommend user to play 12x12 format on tablet.
3.As a language learner, I want to track the words with difficulties for me, so that I can practice them more times.
Scenario 1:
Given: User is finished of filling the sudoku game grid.
When: Users click the check button.
Then:  If there is a false, the sudoku game system will record the top of most failed vocabs and let them to appear in sudoku next time.
4.As a teacher or student, I want to add some new chapters or words for me to practice, so that my new vocabulary can always update.
Scenario 1:
Given: There is a page to select different subject or add a new one.
When: Users click the selection to add a new subject of list of words.
Then:  Select the CSV file from users’ devices and name a new subject. Then this new subject will be selectable next time.
5.As a language learner, I want to practice the listening part of new language, so that I want to have a listening mode to make all words to be pronounced.
Scenario 1:
Given: The listening mode works well.
When: Users click the listening button exchange button.
Then:  All the words in the sudoku grid will change to a number and press each cell there will be a corresponding word to speak out.
Scenario 2:
Given: The listening mode is on (already changed to corresponding numbers on the grid).
When: Users click one of the numbers on the grid.
Then:  The game system will read out the word corresponding to number.
Scenario 3:
Given: The listening mode is on (already changed to corresponding numbers on the grid).
When: Users click one of the words on the selection table on the bottom.
Then:  The game system will read out the word clicked on the table.
6.As a language learner, I want to use this app in different devices, so that it will be convenient for me to play it.
Scenario 1:
Given: The sudoku grid game page is displayed.
When: Users play sudoku game on phone and tablet.
Then:  If users rotate the phone or tablet, this game can be displayed horizontally and vertically.
7.As a language learner, I want to transfer languages freely when I play sudoku game, so that I can choose different mode for me to play.
Scenario 1: Provided a horizontal and vertical mode for each devices, included the cell phone and tablet.
Given: The sudoku grid game page is displayed.
When: Users click the exchange the button on the top right side.
Then:  The game grid will change to corresponding language and also the selection table on the bottom.


Final Iteration 

User Stories:
1.As a teachers and students of elementary school and secondary school, we want a simple sudoku problem, so that we can do some simple practices.
Scenario 1: 
Given: There are selections of different sizes of sudoku game.
When: Users can select 4x4 sizes of easier game.
Then:  The sudoku grid will change to 4x4 format.

Scenario 2:
Given: There are selections of different sizes of sudoku game.
When: Users can select 6x6 sizes of easier game.
Then:  The sudoku grid will change to 6x6 format.

2.As a high level language learner, we want a hard sudoku problem, so that we can do some difficult practices.
Scenario 1: 
Given: There are selections of different sizes of sudoku game.
When: Users can select 12x12 sizes of hard game on the tablet.
Then:  The sudoku grid will change to 12x12 format on the tablet.

Scenario 2: 
Given: There are selections of different sizes of sudoku game.
When: Users can select 12x12 sizes of hard game on the cell phone.
Then:  The sudoku grid will change to 12x12 format, but it will recommend user to play 12x12 format on tablet.

3.As a language learner, I want to track the words with difficulties for me, so that I can practice them more times.
Scenario 1: 
Given: User is finished of filling the sudoku game grid.
When: Users click the check button.
Then:  If there is a false, the sudoku game system will record the top of most failed vocabs and let them to appear in sudoku next time.

4.As a teacher or student, I want to add some new chapters or words for me to practice, so that my new vocabulary can always update.
Scenario 1: 
Given: There is a button to select adding a new subject on the home page.
When: Users click the selection to add a new subject of list of words.
Then:  Select the CSV file from users’ devices and name a new subject. Then this new subject will be selectable next time.

5.As a language learner, I want to practice the listening part of new language, so that I want to have a listening mode to make all words to be pronounced.
Scenario 1: 
Given: The listening mode works well.
When: Users click the listening button exchange button.
Then:  All the words in the sudoku grid will change to a number and press each cell there will be a corresponding word to speak out.

Scenario 2:
Given: The listening mode is on (already changed to corresponding numbers on the grid).
When: Users click one of the numbers on the grid.
Then:  The game system will read out the word corresponding to number.

Scenario 3:
Given: The listening mode is on (already changed to corresponding numbers on the grid).
When: Users click one of the words on the selection table on the bottom.
Then:  The game system will read out the word clicked on the table.

6.As a language learner, I want to use this app in different devices, so that it will be convenient for me to play it.
Scenario 1: 
Given: The sudoku grid game page is displayed.
When: Users play sudoku game on phone and tablet.
Then:  If users rotate the phone or tablet, this game can be displayed horizontally and vertically.

7.As a language learner, I want to transfer languages freely when I play sudoku game, so that I can choose different mode for me to play.
Scenario 1: 
Given: The sudoku grid game page is displayed.
When: Users click the exchange the button on the top(The first button).
Then:  The game grid will change to corresponding language and also the selection table on the bottom.

8.As a beginner of sudoku player, I want to have a right-now check button on gameplay page, so that the system will check my selection anytime during playing the game. if there is no error, the system will tell me. Also, system will also tell me if an error occurs.
Scenario 1:
Given: The sudoku grid game is displayed.
When: User fills the blanks in the game page which is correct and click the right-now check button.
Then: System will pop out a notice which is “Correct So Far”.

Scenario 2:
Given: The sudoku grid game is displayed.
When: User fills the blanks in the game page which is false and click the right-now check button.
Then: System will pop out a notice which is “Something Incorrect”.

9.As a language learner, I want to have a clock to track how many time I use for the game, so that I can know how much time I use for the game each time.
Scenario 1:
Given: The sudoku grid game is played.
When: User starts playing the Sudoku game.
Then: There is a timer which records the time users use during each Sudoku game.

10.As a language learner, I want to be able to select a difficulty before playing, so that when I am first learning a language, the puzzle would not be too difficult,
however as I start to get more comfortable with the language, I can increase the difficulty. 
Scenario 1:
Given: User open Sudoku game app and go to the home page which has a button named “Difficulty”.
When: User click difficulty selection button and choose easy mode.
Then: There will n/2 blanks available for n x n grid.

Scenario 2:
Given: User open Sudoku game app and go to the home page which has a button named “Difficulty”.
When: User click difficulty selection button and choose normal mode.
Then: There will (n^2)/(6+n) blanks available for n x n grid.

Scenario 3:
Given: User open Sudoku game app and go to the home page which has a button named “Difficulty”.
When: User click difficulty selection button and choose hard mode.
Then: There will (n^2)/(3+n) blanks available for n x n grid.

Scenario 4:
Given: User open Sudoku game app and go to the home page which has a button named “Difficulty”.
When: User click difficulty selection button and choose tough mode.
Then: There will (n^2)/(2+n) blanks available for n x n grid.

11.As a language learner, I want to select different subjects to be my resources for Sudoku game, so that I can practice any subject I want in this game.
Scenario 1:
Given: User open Sudoku game app and go to the home page which has a button named “Subject”.
When: User click subject selection button and choose easy subjects.
Then: When click play button, the sudoku grid will choose words from the selected subject.

12.As a language learner, I want to be able to switch to a new sudoku game during playing, so that I can always choose to play the one I think interesting.
Scenario 1:
Given: The sudoku grid game is played.
When: User click the renew button on the top(the third button).
Then: The Sudoku game page will refresh and display a new game for playing.

13.As a language learner, I want to have some background music during playing Sudoku game, so that I can feel relaxed while playing game. 
Scenario 1:
Given: The sudoku game app is downloaded.
When: User open the app and go to the gameplay page.
Then: There are two different background music on home page and game page.