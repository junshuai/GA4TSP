import java.util.ArrayList;

public final class OrderCrossover implements Crossover {
    public ArrayList<Chromosome> operate(Chromosome father, Chromosome mother) {
        ArrayList<Integer> gene1 = father.getGenes();
        ArrayList<Integer> gene2 = mother.getGenes();
        int n = gene1.size();
        int cutpoint1 = (int)(Math.random() * n);
        int cutpoint2 = (int)(Math.random() * n);
        int cutpointLeft = Math.min(cutpoint1, cutpoint2);
        int cutpointRight = Math.max(cutpoint1, cutpoint2);

        ArrayList<Integer> syntheticGene1 = new ArrayList<Integer>(n);
        ArrayList<Integer> syntheticGene2 = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            syntheticGene1.add(-1);
            syntheticGene2.add(-1);
        }
        for (int i = cutpointLeft; i < cutpointRight; i++) {
            syntheticGene1.set(i, gene2.get(i));
            syntheticGene2.set(i, gene1.get(i));
        }

        int p1 = cutpointRight, p2 = cutpointRight;
        for (int i = cutpointRight; i < n + cutpointLeft; i++) {
            while (syntheticGene1.contains(gene1.get(p1 % n))) {
                p1++;
            }
            syntheticGene1.set(i % n, gene1.get(p1 % n));
            while (syntheticGene2.contains(gene2.get(p2 % n))) {
                p2++;
            }
            syntheticGene2.set(i % n, gene2.get(p2 % n));
        }
        
        ArrayList<Chromosome> children = new ArrayList<Chromosome>();
        children.add(new Chromosome(father.getTSP(), syntheticGene1));
        children.add(new Chromosome(mother.getTSP(), syntheticGene2));

        return children;
    }
}
