public class ExchangeMutation implements Mutation {
    public Chromosome operate(final Chromosome chromosome) {
        Chromosome mutant = chromosome.clone();

        int len = mutant.length();
        int left = (int)(Math.random() * len);
        int right = (int)(Math.random() * len);

        Integer temp = mutant.genes.get(left);
        mutant.genes.set(left, mutant.genes.get(right));
        mutant.genes.set(right, temp);
        return mutant;
    }
}
