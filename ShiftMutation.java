public class ShiftMutation implements Mutation {
    public Chromosome operate(final Chromosome chromosome) {
        Chromosome mutant = chromosome.clone();

        int len = mutant.length();
        int offset = (int)(Math.random() * len);

        Integer temp = mutant.genes.get(2);
        for (int i = 0; i < len; i++) {
            mutant.genes.set(i, chromosome.genes.get((i + offset) % len));
        }

        return mutant;
    }
}
