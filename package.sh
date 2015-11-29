javac src/*.java -d classes && cd classes && \
	echo 'Main-Class: TSPSolver' > manifest.txt && jar cvfm ../jar/TSPSolver.jar manifest.txt *.class && \
	echo 'Main-Class: MutationRateExperiment' > manifest.txt && jar cvfm ../jar/MutationRateExperiment.jar manifest.txt *.class && \
	echo 'Main-Class: CrossoverExperiment' > manifest.txt && jar cvfm ../jar/CrossoverExperiment.jar manifest.txt *.class && \
	rm manifest.txt && cd ..
