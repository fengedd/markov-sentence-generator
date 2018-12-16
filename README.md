# Markov Sentence Generator
Generates sentences through a statistical approach by using Markov Chain and Bigrams as a language model. Made with Stanford CoreNLP.

## How it works
The program takes a text file and parses out each sentence and forms bigrams out of the sentences. By the Markov property, the bigram frequencies are used to calculate the transition probabilities and to form the transition graph. The Markov Chain is modeled with a red-black tree and an adjacency list graph. See https://web.stanford.edu/class/cs124/lec/languagemodeling.pdf for more info.


## Installation
In the markov-sentence-generator directory type:

```sh
> java -jar Markov.jar
```
## Usage
For best results use sentences delimited by each line.
```sh
> Enter text file name in directory (include .txt): 'sample.txt'

> Enter number of sentences to generate: '10'
```

Output:
```sh
[Not in a house . , 
Not in a mouse ? , 
I do not like them here or there . , 
So I do not like them In the dark ? , 
I could not like them , Sam I would not like them here or there . , 
I will eat them in a car , In the dark ! , 
I will let me be . , 
In a tree ? , 
You let me be ! , 
I will not , could not . ]
```

## License
[MIT](https://choosealicense.com/licenses/mit/)