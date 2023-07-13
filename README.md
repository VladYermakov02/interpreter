# Interpreter
Interpreter to my language which is similar to Java

## Project description
The first task is create is to decribe language and write BNF of the language.

The second task is a lexical analyzer. A lexical analyser identifies and recognises classes of tokens. Performs the task by studying the classes of tokens and creating an algorithm that can split the program code into tokens and distinguish between the classes and subclasses of tokens to which they belong, according to the specifications. 

The main task of a lexical analyser is to extract and recognise a token. The result of its work is the selected token, as well as the class number and its number in the class. 
The existed classes: 
- identifiers; 
- reserved words; 
- separators (; , . . : ^ |, etc.); 
- operation signs (+ - / * = := < >, etc.); 
- service words (begin, end); 
- literals (explicit values: "a", 1..5, a:=1.8, etc;)
- terms (a[i]^.b, etc.);
- comments.

The third task is to create a parser that, based on the previously created lexical analyzer, checks the code for belonging to the described grammar or for syntax errors. Complete the task by writing an LL(1) grammar based on the Bekus-Naur form, creating a state machine for the selected states and an algorithm that would allow the input code to be checked for compliance with the states of the state machine.  

Then, description of the chosen method of implementing the parser.
To solve our task, we first convert the grammar to LL(1) on the basis of the previously created BNF. The created grammar was then used to distinguish states, on the basis of which a store-and-forward automaton was created, which we use to parse the submitted code.

The forth task is task statement.
Generate intermediate code by building descriptors based on the lexical and parsers created in the previous labs. The program should output the data of function descriptors (type, name, number of the first token of the function body, number of the last token of the function body, type of arguments, argument names) as a demonstration example.

And last but not least is	theoretical information.
To generate intermediate code, you use the creation of descriptors based on the language grammar. This method consists in adding semantic elements to certain parts of the grammar, i.e. in our case, adding actions to states. In this way, the process of creating a parser and starting code generation is linked. In this case, the parser can exist as a separate operating mechanism, but actions are added to the grammar, which then also correspond to the states of the automaton and are present in the code. This way, you can build descriptors for the main constructs of the language by assigning actions to individual parts of the grammar, such as loops and functions. 
My program demonstrates adding actions to the states associated with creating functions. Such grammar modifications are displayed on the automaton in the form of function references, as well as in the state classes and the parser itself. Function descriptors include the following information: type of return value, name, argument type, argument names, number of the first token of the function body, and number of the last token of the function body.

## Languages and technologies used
Everything written in Java

## Screenshots
### LL1 Grammar
![image_2023-07-12_16-34-19](https://github.com/VladYermakov02/interpreter/assets/129091482/fe621f8d-3ab3-4068-a700-344f8cc00764)
![image_2023-07-12_16-34-55](https://github.com/VladYermakov02/interpreter/assets/129091482/9ad1f83a-d660-4d30-abc7-be26ea5b965c)
### Example of syntax
![image_2023-07-11_16-59-14](https://github.com/VladYermakov02/interpreter/assets/129091482/d3e3b311-1931-40eb-8ca9-96661bd255de)
### Example of lexical analyzer
![image_2023-07-11_16-59-35](https://github.com/VladYermakov02/interpreter/assets/129091482/93add103-e3bd-4908-b6a8-e2438c32be66)
![image_2023-07-11_17-00-36](https://github.com/VladYermakov02/interpreter/assets/129091482/94944b1b-bd4a-48be-9326-a398cc16de46)
![image_2023-07-11_17-00-40](https://github.com/VladYermakov02/interpreter/assets/129091482/4e6d93d9-fc5f-4553-9a5a-54c2dcd45863)
![image_2023-07-11_17-00-43](https://github.com/VladYermakov02/interpreter/assets/129091482/9b6a4aee-3654-476c-99c2-0b456a6cf56e)
![image_2023-07-11_17-01-13](https://github.com/VladYermakov02/interpreter/assets/129091482/7b2f36c9-d867-46fc-87c5-9992ced84825)
![image_2023-07-11_17-02-24](https://github.com/VladYermakov02/interpreter/assets/129091482/b9b3ed4c-a6af-40b0-b424-0443114c8ff0)
### Example of lexical analyzer (wrong syntax)
![image](https://github.com/VladYermakov02/interpreter/assets/129091482/0e2865c0-2952-41db-8be1-b92ae8c8b4f8)
![image](https://github.com/VladYermakov02/interpreter/assets/129091482/8168cf81-8a23-45d4-b31b-6f115070eb68)
### Example of function descriptors analyzer
![image_2023-07-11_17-02-38](https://github.com/VladYermakov02/interpreter/assets/129091482/0674a21f-2261-434c-8c79-cba7fe3c686f)
