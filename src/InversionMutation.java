public class InversionMutation implements Mutation {
    public Chromosome operate(final Chromosome chromosome) {
        Chromosome mutant = chromosome.clone();

        int len = mutant.length();
        int left = (int)(Math.random() * len);
        int right = (int)(Math.random() * len);
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        for (int i = left, j = right - 1; i < j; i++, j--) {
            Integer temp = mutant.genes.get(i);
            mutant.genes.set(i, mutant.genes.get(j));
            mutant.genes.set(j, temp);
        }

        return mutant;
    }
}
