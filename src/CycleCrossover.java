import java.util.ArrayList;

public class CycleCrossover implements Crossover {
    public ArrayList<Chromosome> operate(final Chromosome father, final Chromosome mother) {
        ArrayList<Integer> gene1 = father.getGenes();
        ArrayList<Integer> gene2 = mother.getGenes();

        int n = gene1.size();

        ArrayList<Boolean> interchangeable = new ArrayList<Boolean>(n);
        for (int i = 0; i < n; i++)
            interchangeable.add(true);

        int last = gene1.get(0);
        int cur = 0;
        interchangeable.set(cur, false);
        while (gene2.get(cur) != last) {
            cur = gene1.indexOf(gene2.get(cur));
            interchangeable.set(cur, false);
        }

        ArrayList<Integer> s1 = new ArrayList<Integer>(n);
        ArrayList<Integer> s2 = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            if (interchangeable.get(i)) {
                s1.add(gene2.get(i));
                s2.add(gene1.get(i));
            } else {
                s1.add(gene1.get(i));
                s2.add(gene2.get(i));
            }
        }

        ArrayList<Chromosome> children = new ArrayList<Chromosome>(2);
        children.add(new Chromosome(father.getTSP(), s1));
        children.add(new Chromosome(father.getTSP(), s2));

        return children;
    }
}
