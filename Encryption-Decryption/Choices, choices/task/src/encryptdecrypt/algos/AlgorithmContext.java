package encryptdecrypt.algos;

// Was also thinking of calling it AlgorithmWorker/AlgorithmOperator
public class AlgorithmContext {
    private AlgorithmStrategy strategy;

//    AlgorithmContext(AlgorithmStrategy strategy) {
//        this.strategy = strategy;
//    }

    // Or, we could call this method "setAlgorithm()"
    public void setStrategy(AlgorithmStrategy strategy) {
        this.strategy = strategy;
    }

    public String getResult(String data, int key) {
        // Here, we're passing the arguments to the method like this: getResult(String data, int key)
        // But we could also pass the arguments directly to "strategy" via their constructors.
        return strategy.getResult(data, key);
    }
}
