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

![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/plus.png)

The parameters of all parts of the system are scalars and are equal to 2. Then, if the type of bond is additive
"we get:

```
Out = P (Control2) * P (R) + P (State1) = 2 * 2 + 2 = 6
```
![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/plus6.png)

If the type of connection is "m" (multiplicative), then

```
Out = P (Control2) * P (R) * P (State1) = 2 * 2 * 2 = 8
```

![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/plus8.png)


At the same time, if the node has several incoming connections, then the outgoing vector gets
by summing all input parameters. For example, add to the previous
occasion is another node.


![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/state7.png)

Here the parameter of the State7 node is 3, and the parameter of its outgoing connection is 1. Then,
the output vector for State1 is:


```
Out = [P (Control2) * P (R Control2 ) * P (State1)] + [P (State7) * P (R State7 ) + P (State1)] =
= [2 * 2 * 2] + [3 * 1 + 2] = 8 + 5 = 13
```
![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/state72.png)


## Operations with numbers

Features of this program allow you to perform the simplest arithmic actions
effects on numbers: addition, multiplication and division.

### Addition of two numbers

In order to add two numbers you need to add two nodes to the panel, the type of connection
choose an additive and communication parameter set to 1. Example:

![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/state8.png)

There:
```
P (State9) = 3, P (R) = 1, P (State8) = 2. 
```
Then:
```
P (State9) * P (R) + P (State8) = 2 * 1 + 3 = 5.
```


![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/add2.png)


### Multiplication of two numbers

n order to multiply two numbers you need to perform all the actions as for complex
instead of an additive relationship, choose a multiplicative. Then the example above will give
following:

```
P (State9) * P (R) * P (State8) = 2 * 1 * 3 = 6
```
![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/mult2.png)


### Division of two numbers

The division of two numbers in this program is different from addition and multiplication. Here
division is implemented by solving the equation ax = b. In order to find b / a you need
add just one node. And to set for him not parameters, but the incoming vector a and outgoing
vector b. At the same time, the parameters "I" and "O" should appear on the screen at the node, indicating
dimension by vector.

![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/div2.png)

Next, the calculation type is marked "Identification" and click "Calculate". In quality of
measure we take the incoming vector is 3, and outgoing - 6. As a result of the calculation:

![alt text](https://github.com/trofimovep/NeiSys/tree/master/src/spec/Description/pic/div22.png)


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

