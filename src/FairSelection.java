public final class FairSelection implements Selection {
    public Chromosome select(Population population) {
        int index = (int)(Math.random() * population.size());
        return population.get(index);
    }
}
