import java.util.ArrayList;

public final class PartiallyMappedCrossover implements Crossover {
    public ArrayList<Chromosome> operate(Chromosome father, Chromosome mother) {
        ArrayList<Integer> g1 = father.getGenes();
        ArrayList<Integer> g2 = mother.getGenes();
        int n = g1.size();

        int cutpoint1 = (int)(Math.random() * n);
        int cutpoint2 = (int)(Math.random() * n);
        int left = Math.min(cutpoint1, cutpoint2);
        int right= Math.max(cutpoint1, cutpoint2);

        ArrayList<Integer> s1 = new ArrayList<Integer>(n);
        ArrayList<Integer> s2 = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            s1.add(-1);
            s2.add(-1);
        }

        for (int i = left; i < right; i++) {
            s1.set(i, s2.get(i));
            s2.set(i, s1.get(i));
        }

        for (int i = left; i < right; i++) {
            if (!s1.contains(s2.get(i))) {
                int p = i;
                while (s2.contains(s1.get(p))) {
                    p = s2.indexOf(s1.get(p));
                }
                s1.set(g2.indexOf(s1.get(p)), s2.get(i));
            }
        }
        for (int i = 0; i < n; i++) {
            if (s1.get(i) == -1) {
                s1.set(i, g2.get(i));
            }
        }

        ArrayList<Integer> s3 = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++)
            s3.add(-1);
        for (int i = left; i < right; i++) {
            s3.set(i, s2.get(i));
        }

        for (int i = left; i < right; i++) {
            if (!s2.contains(s3.get(i))) {
                int p = i;
                while (s3.contains(s2.get(p))) {
                    p = s3.indexOf(s2.get(p));
                }
                s2.set(g1.indexOf(s2.get(p)), s3.get(i));
            }
        }
        for (int i = 0; i < n; i++) {
            if (s2.get(i) == -1) {
                s2.set(i, g1.get(i));
            }
        }

        ArrayList<Chromosome> children = new ArrayList<Chromosome>();
        children.add(new Chromosome(father.getTSP(), s1));
        children.add(new Chromosome(father.getTSP(), s2));
        return children;
    }
}
