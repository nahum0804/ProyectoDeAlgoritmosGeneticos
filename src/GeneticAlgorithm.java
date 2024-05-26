import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Genetic Algorithm
 */
public class GeneticAlgorithm {

    /**
     * Generate initial population of chromosomes
     * @param items
     * @return
     */
    private static List<List<Integer>> initialPopulation(List<Object> items){
        List<Integer> chromosome = new ArrayList<>();
        List<List<Integer>> population = new ArrayList<>();
        int n = items.size();
        Random random = new Random();
        int randomValue = 0;
        int initialPopulation;
        int numberSons;

        if(n == 5){
            initialPopulation = 3;
            numberSons = 6;

            for(int i = 0; i < initialPopulation; i++){
                for(int j = 0; j < n; j++){
                    randomValue = random.nextInt(2);
                    chromosome.add(randomValue);
                }
                population.add(chromosome);
                chromosome = new ArrayList<>();
            }

        } else{
            initialPopulation = n;
            numberSons = n * 2;
            for(int i = 0; i < initialPopulation; i++){
                for(int j = 0; j < n; j++){
                    randomValue = random.nextInt(2);
                    chromosome.add(randomValue);
                }
                population.add(chromosome);
                chromosome = new ArrayList<>();
            }
        }

        return population;
    }

    /**
     * Crossover between chromosomes
     * @param population
     * @param numberSons
     * @return
     */
    private static List<List<Integer>> crossover(List<List<Integer>> population, int numberSons){
        List<List<Integer>> sons = new ArrayList<>();
        List<Integer> son = new ArrayList<>();
        Random random = new Random();
        int randomValue = 0;
        int n = population.get(0).size();
        int crossoverPoint = 0;

        for(int i = 0; i < numberSons; i++){
            int father1 = random.nextInt(population.size());
            int father2 = random.nextInt(population.size());
            crossoverPoint = random.nextInt(n);

            for(int j = 0; j < n; j++){
                if(j < crossoverPoint){
                    son.add(population.get(father1).get(j));
                } else{
                    son.add(population.get(father2).get(j));
                }
            }
            sons.add(son);
            son = new ArrayList<>();
        }

        return sons;
    }

    private static List<List<Integer>> mutation(List<List<Integer>> population, int numberMutations){
        List<List<Integer>> mutatedPopulation = new ArrayList<>();
        List<Integer> mutatedChromosome = new ArrayList<>();
        Random random = new Random();
        int n = population.get(0).size();
        int mutationPoint = 0;
        int randomValue = 0;

        for(int i = 0; i < numberMutations; i++){
            int father = random.nextInt(population.size());
            mutationPoint = random.nextInt(n);

            for(int j = 0; j < n; j++){
                if(j == mutationPoint){
                    if(population.get(father).get(j) == 0){
                        randomValue = 1;
                    } else{
                        randomValue = 0;
                    }
                    mutatedChromosome.add(randomValue);
                } else{
                    mutatedChromosome.add(population.get(father).get(j));
                }
            }
            mutatedPopulation.add(mutatedChromosome);
            mutatedChromosome = new ArrayList<>();
        }

        return mutatedPopulation;
    }

    /**
     * Selection of the best chromosomes
     * @param population
     * @param items
     * @param maxValue
     * @return
     */
    private static List<List<Integer>> fitness(List<List<Integer>> population, List<Object> items, int maxValue){
        List<List<Integer>> selectedPopulation = new ArrayList<>();
        List<Integer> selectedChromosome = new ArrayList<>();
        int n = population.get(0).size();
        int totalValue = 0;
        int totalWeight = 0;
        int value = 0;
        int weight = 0;
        int randomValue = 0;
        Random random = new Random();

        for(List<Integer> chromosome : population){
            for(int i = 0; i < n; i++){
                if(chromosome.get(i) == 1){
                    value += items.get(i).getValue();
                    weight += items.get(i).getWeight();
                }
            }
            if(weight <= maxValue){
                selectedPopulation.add(chromosome);
            } else{
                for(int i = 0; i < n; i++){
                    randomValue = random.nextInt(2);
                    selectedChromosome.add(randomValue);
                }
                selectedPopulation.add(selectedChromosome);
                selectedChromosome = new ArrayList<>();
            }
            value = 0;
            weight = 0;
        }

        return selectedPopulation;
    }


    /**
     * Run the algorithm
     * @param elements
     * @param maxValue
     */
    public static void run(List<Object> elements, int maxValue) {
        System.out.println("Genetic Algorithm!");
    }
}
