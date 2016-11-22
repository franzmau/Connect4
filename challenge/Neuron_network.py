#### Libraries
import random

# Library for Algebra
import numpy

class Neuron_network(object):

    def __init__(self, layers_sizes):
	
		# self
		# layers_size: Number of neurons per layer, i.e. [2,2,2] 2 neurons for input, 2 for hidden layer, 2 for output
        
		self.layers_sizes = layers_sizes
        self.number_layers = len(layers_sizes) 
        self.biases = [numpy.random.randn(y, 1) for y in layers_sizes[1 :]]
        self.weights = [numpy.random.randn(y, x) for x, y in zip(layers_sizes[: -1], layers_sizes[1 :])]

    # Activation function's methods
	def sigmoid_function(z):
		return 1.0/(1.0 + numpy.exp(-z))

	def sigmoid_prime(z):
		return sigmoid_function(z) * (1 - sigmoid_function(z))
	
	def calculate_output(self, a):
	
		# self
		# a: input
		
        for bias, weight in zip(self.biases, self.weights):
            a = sigmoid_function(numpyp.dot(weight, a) + bias)
        return a

    def slow_gradient_descent(self, training_set, epochs, batch_size, learning_rate, test_set = None):
        
		# self
		# training_set : Complete set of training data
		# epochs: Maximum number of iterations for not overfitting
		# batch_size: For not iterating over all the data a reduced number of groups is used
		# learning_rate
		# test_set: Complete set of test data for testing the network
		
        if test_set: 
			n_test = len(test_set)
			
        n = len(training_set)
		
        for i in xrange(epochs):
            random.shuffle(training_set)
            batches = [training_data[j : j + batch_size] for j in xrange(0, n, batch_size)]
            
			for batch in batches:
                self.update_batch(batch, learning_rate)
				
            if test_data:
                print "Iteration {0}: {1} of {2}".format(
                    i, self.evaluate(test_data), n_test)
            else:
                print "Iteration {0} complete".format(i)

    def update_batch(self, batch, learning_rate):
        
		# self
		# batch: batch for updating the biases and weights
		# learning_rate 
		
        new_biases = [numpy.zeros(biases.shape) for biases in self.biases]
        new_weights = [numpy.zeros(weights.shape) for weights in self.weights]
        for x, y in batch:
            delta_new_biases, delta_new_weights = self.back_propagation(x, y)
            new_biases = [new_bias + delta_bias for new_bias, delta_bias in zip(new_biases, delta_new_biases)]
            new_weights = [new_weight + delta_weight for new_weight, delta_weight in zip(new_weights, delta_new_weights)]
        self.weights = [weight - (learning_rate / len(batch)) * new_weight for weight, new_weight in zip(self.weights, new_weights)]
        self.biases = [bias - (learning_rate / len(batch)) * new_bias for bias, new_bias in zip(self.biases, new_biases)]

    def back_propagation(self, x, y):
		
		# self
		# x
		# y
		
        new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
        new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
		
        # Feeding forward process
		
        activation = x
        activations = [x]
		
        z_vectors = [] 
		
        for bias, weight in zip(self.biases, self.weights):
            z = numpy.dot(weight, activation) + bias
            z_vectors.append(z)
            activation = sigmoid(z)
            activations.append(activation)
			
        # Backward process
		
        delta = self.cost_derivative(activations[-1], y) * \ sigmoid_prime(z_vectors[-1])
        new_biases[-1] = delta
        new_weights[-1] = numpy.dot(delta, activations[-2].transpose())

        for reverse_layer in xrange(2, self.num_layers):
            z = z_vectors[-reverse_layer]
            sigmoid_p = sigmoid_prime(z)
            delta = numpy.dot(self.weights[1 - reverse_layer].transpose(), delta) * sigmoid_p
            new_biases[-reverse_layer] = delta
            new_weights[-reverse_layer] = numpy.dot(delta, activations[-1 - reverse_layer].transpose())
        return (new_biases, new_weights)

    def evaluate_outputs(self, test_set):
        
		# returns the evaluations of the test sets
		
		# self
		# test_set: Sets of test set for testing the score of our network
		
        tests_outputs = [(np.argmax(self.calculate_output(x)), y) for (x, y) in test_set]
        return sum(int(x == y) for (x, y) in tests_outputs)

    def cost_derivative(self, output_activations, y):
        
		# returns the derivative of the cost function Cx
		
		# self
		# output_activations: output activations vector for the tested sets
		# y: 
        return (output_activations - y)

