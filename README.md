# Email similarity

## Problem

Write a small application in Java that allows detecting spam emails by similarity of the email body text.

The background to this is that spammers usually send similar emails to a large number of recipients, but to 
avoid being detected as spammers they often change small parts of that email when sent to different recipients. 

Let's assume we want to detect email users sending out spam, and we can read their emails. Your task is to write 
a routine that gets a set of email body texts and assigns a spam probability to each of them depending on the 
similarity to the other emails in the set. The more similar it is to the other emails, the more likely it is 
a spam email.

## Preamble

Let's assume that we don't want to solve this problem using Machine Learning. If we had a good dataset, we could train a
neural network to do this job...

Still, as a programming exercise, assume that we are interested in a non-statistical algorithm.

## Solution 1

Consider the [Longest Common Subsequence](https://en.wikipedia.org/wiki/Longest_common_subsequence) problem.

To evaluate the similarity of two texts, we can  find the longest common subsequence and compare it to the longest of the two texts:
if $lcs$ is the length of the longest common subsequence and $l$ is the length of the longest text, then we estimate the similarity by $lcs / l$.

We can implement the LCS algorithm as suggested [here](https://en.wikipedia.org/wiki/Longest_common_subsequence):
1. we build the longest common subsequence matrix first;
2. we backtrack the sequences, using the matrix to reconstruct the longest common subsequence.

### Run

There is a classic Java application in `emailsimilarity.similarity.lcs` named `LCSSimilarityApp`. 

Otherwise, there is also the test `EmailLCSSimilarityTest`.

## Solution 2

We first have to provide a meaning to "text similarity" which allows us to implement it.

Since this is a simple task, we provide a simplified, questionable definition.

Let's start with some assumptions:
1. similarity $s$ is a function taking two texts as input and returning a real number (float or double) as output;
2. we express similarity as a real number (float or double) in the interval $[0,1]$; 
3. given a text $t$, $s(t,t) = 1$, i.e., a text is the most possible similar to itself; 
4. given two texts $t_1, t_2$ with no symbol in common, $s(t_1, t_2) = 0$;
5. **for the sake of simplicity, we ignore word order in this phase**.

The last assumption means that `the brown fox` has similarity of $1$ with `fox the brrown`, which is clearly an oversimplification. Still, 
to tackle the problem, we introduce this very strong assumption.

A possible weak justification for the last assumption is that, in the context of emails, changing too much the word order invalidates
the meaning of the text, therefore, we rely on some kind of "semantic and syntactic sentence rigidity", which implies that, given the words, there are
only a few numbers of syntactically correct sentences possibles using those words and having sense.

This is clearly questionable. Just think of the examples:
- "the man eats the fish",
- "the fish eats the man".
They have clearly different meanings, still, out algorithm will assign a similarity of $1$ to them.

This is a weakness to work on...

The approach is the following:
- we define an algorithm that compares two words and returns the percentage of letters that they have in common.
- we define an algorithm that compares two sentences and returns their similarity as: $(c + \sum w_i) / l$
  where:
  - $c$ is the number of words that they have in common,
  - $w_i$ is the similarity of a word from the _shortest_ sentence **not present in the longer one** with the maximally similar word from the _longer_ sentence;
  - the sum runs over all words from the _shortest_ sentence that _do not appear_ in the longer one;
  - $l$ is the length of the longer sentence.

In the calculation of $w_i$ there is a possible calculation ordering to tackle.

In other words (using the notation above):
- we compute the number of words in common $c$;
- for each word not in common from the shorter sentence, we compute the maximal similarity from a word of the longer sentence; the latter will not 
  be available for the subsequent calculations (avoid pairing the same word twice);
- we sum all these word-to-word similarities into $s$;
- we sum $c + s$ and we divide by $l$.

### Run

There is a classic Java application in `emailsimilarity.similarity.text` named `TextSimilarityApp`.

Otherwise, there is also the test `EmailTextSimilarityTest`.
