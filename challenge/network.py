##Libraries
import random

# Third-party libraries
import numpy

class Neuron_Network(object):

    def __init__(self, layers_sizes):
        self.number_layers = len(layers_sizes)
        self.layers_sizes = layers_sizes
        self.biases = [numpy.random.randn(y, 1) for y in layers_sizes[1:]]
        self.weights = [numpy.random.randn(y, x) for x, y in zip(layers_sizes[:-1], layers_sizes[1:])]

    def feedforward(self, a):
        for bias, weight in zip(self.biases, self.weights):
            a = sigmoid(numpy.dot(weight, a) + bias)
        return a

    def Stochastic_Gradient_Descent(self, training_data, epochs, batch_size, learning_rate, test_data = None):
        if test_data:
            test_length = len(test_data)
        n = len(training_data)
        for cur_iter in xrange(epochs):
            random.shuffle(training_data)
            batches = [training_data[k:k+batch_size] for k in xrange(0, n, batch_size)]
            for batch in batches:
                self.update_batch(batch, learning_rate)
            if test_data:
                print "Iteration {0}: {1} of {2}".format(cur_iter, self.evaluate(test_data), test_length)
            else:
                print "Iteration {0} complete".format(cur_iter)

    def update_batch(self, batch, learning_rate):
        new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
        new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
        for x, y in batch:
            delta_new_biases, delta_new_weights = self.back_propagation(x, y)
            new_biases = [new_bias + delta_bias for new_bias, delta_bias in zip(new_biases, delta_new_biases)]
            new_weights = [new_weight + delta_weight for new_weight, delta_weight in zip(new_weights, delta_new_weights)]
        self.weights = [weight - (learning_rate / len(batch)) * new_weight for weight, new_weight in zip(self.weights, new_weights)]
        self.biases = [bias - (learning_rate / len(batch)) * new_bias for bias, new_bias in zip(self.biases, new_biases)]

    def back_propagation(self, x, y):
        new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
        new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
        # Feed Forward Propagation
        activation = x
        activations = [x]
        z_vectors = []
        for bias, weight in zip(self.biases, self.weights):
            z = numpy.dot(weight, activation) + bias
            z_vectors.append(z)
            activation = sigmoid(z)
            activations.append(activation)
        # Backward Propagation
        delta = self.cost_derivative(activations[-1], y) * sigmoid_prime(z_vectors[-1])
        new_biases[-1] = delta
        new_weights[-1] = numpy.dot(delta, activations[-2].transpose())

        for reverse_layer in xrange(2, self.number_layers):
            z = z_vectors[-reverse_layer]
            prime_sigmoid = sigmoid_prime(z)
            delta = numpy.dot(self.weights[1 - reverse_layer].transpose(), delta) * prime_sigmoid
            new_biases[-reverse_layer] = delta
            new_weights[-reverse_layer] = numpy.dot(delta, activations[-reverse_layer - 1].transpose())
        return (new_biases, new_weights)

    def evaluate(self, test_data):
        test_results = [(numpy.argmax(self.feedforward(x)), y) for (x, y) in test_data]
        return sum(int(x == y) for (x, y) in test_results)

    def predict_digit(self, digit_data):
        return (numpy.argmax(self.feedforward(digit_data)))

    def cost_derivative(self, output_activations, y):
        return (output_activations-y)

##General Purpose
def sigmoid(z):
    return 1.0/(1.0+numpy.exp(-z))

def sigmoid_prime(z):
    return sigmoid(z)*(1-sigmoid(z))
