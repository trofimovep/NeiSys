# DO NOT OPEN THIS IF YOU WANT TO SEE THE QUALITY OF MY CODE. IT'S AWFULL AND IN A REWRITING. CAUSE IT'S MY FIRST APP, WHICH I DON'T REMOVE CAUSE IT USED TO SOME CALCULATING AT UNIVERSITY. 

# Neighborhood Systems

This program is designed to work with local systems,
meters (nodes and links) of which are scalars, vectors and matrices.
The program's algorithm is as follows: starting from the first node in the direction of the links
parameters of nodes, and for nodes of end nodes of the state (State) considers the output
vector. We call the end states those states that have no outgoing connections. Wherein
a node can be affected not only by the nodes from which it is communicating, but the incoming
node (right mouse button -> Set incoming vector).
If any node has no parameters, then in the process of calculating the
The program should automatically solve the identification problem for this node.
The program allows you to perform simple arithmetic operations on scalars
and matrices.

![main](https://user-images.githubusercontent.com/23281289/47664359-f0c4f700-dbaf-11e8-9e02-f7d8faa60898.png)

## Getting Started

When connecting a connection between two nodes: the parameters of the node from which the
multiply by the communication parameters and affect the second node depending on the type of communication:
addition or multiplication.
Consider an example. Suppose there are two connected nodes:

![plus](https://user-images.githubusercontent.com/23281289/47664361-f0c4f700-dbaf-11e8-9ae4-41a43530b27f.png)

The parameters of all parts of the system are scalars and are equal to 2. Then, if the type of bond is additive
"we get:

```
Out = P (Control2) * P (R) + P (State1) = 2 * 2 + 2 = 6
```
![plus6](https://user-images.githubusercontent.com/23281289/47664364-f0c4f700-dbaf-11e8-8317-4dddab0c499f.png)

If the type of connection is "m" (multiplicative), then

```
Out = P (Control2) * P (R) * P (State1) = 2 * 2 * 2 = 8
```

![plus8](https://user-images.githubusercontent.com/23281289/47664366-f15d8d80-dbaf-11e8-9367-84bd5f6d01f6.png)


At the same time, if the node has several incoming connections, then the outgoing vector gets
by summing all input parameters. For example, add to the previous
occasion is another node.

![state7](https://user-images.githubusercontent.com/23281289/47664367-f15d8d80-dbaf-11e8-8d32-5ab13fee056e.png)
Here the parameter of the State7 node is 3, and the parameter of its outgoing connection is 1. Then,
the output vector for State1 is:


```
Out = [P (Control2) * P (R Control2 ) * P (State1)] + [P (State7) * P (R State7 ) + P (State1)] =
= [2 * 2 * 2] + [3 * 1 + 2] = 8 + 5 = 13
```
![state72](https://user-images.githubusercontent.com/23281289/47664369-f15d8d80-dbaf-11e8-9b72-4497797989b0.png)



## Operations with numbers

Features of this program allow you to perform the simplest arithmic actions
effects on numbers: addition, multiplication and division.

### Addition of two numbers

In order to add two numbers you need to add two nodes to the panel, the type of connection
choose an additive and communication parameter set to 1. Example:

![state8](https://user-images.githubusercontent.com/23281289/47664368-f15d8d80-dbaf-11e8-869b-90674c4f4ca2.png)

There:
```
P (State9) = 3, P (R) = 1, P (State8) = 2. 
```
Then:
```
P (State9) * P (R) + P (State8) = 2 * 1 + 3 = 5.
```


![add2](https://user-images.githubusercontent.com/23281289/47664356-f02c6080-dbaf-11e8-84ca-f8b5db9272f8.png)


### Multiplication of two numbers

n order to multiply two numbers you need to perform all the actions as for complex
instead of an additive relationship, choose a multiplicative. Then the example above will give
following:

```
P (State9) * P (R) * P (State8) = 2 * 1 * 3 = 6
```
![mult2](https://user-images.githubusercontent.com/23281289/47664360-f0c4f700-dbaf-11e8-871b-80ad42cd2ba1.png)


### Division of two numbers

The division of two numbers in this program is different from addition and multiplication. Here
division is implemented by solving the equation ax = b. In order to find b / a you need
add just one node. And to set for him not parameters, but the incoming vector a and outgoing
vector b. At the same time, the parameters "I" and "O" should appear on the screen at the node, indicating
dimension by vector.

![div2](https://user-images.githubusercontent.com/23281289/47664357-f02c6080-dbaf-11e8-977e-95afabb3828b.png)

Next, the calculation type is marked "Identification" and click "Calculate". In quality of
measure we take the incoming vector is 3, and outgoing - 6. As a result of the calculation:

![div22](https://user-images.githubusercontent.com/23281289/47664358-f02c6080-dbaf-11e8-8792-fa5703e97202.png)


## Matrix actions

In this program, actions with matrices are similar to actions with numbers. The only thing
What is worth mentioning separately is that in the case of the identification task, the node will issue
transition matrix in the Euclidean basis in the vector case and pseudoinverse in the matrix case.




## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

