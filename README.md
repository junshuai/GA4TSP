# Project Directory

    classes/    - java compiled classes
    data/       - data files
    jar/        - jar files
    package.sh  - a shell script to compile and package project files into jar files
    res/        - directory for program to store result files
    scripts/    - some python scripts to draw a result file
    src/        - code source


# How to run

    java -jar jar/TSPSolver.jar data/ATT48.txt	    # solve the ATT48 TSP
    java -jar jar/TSPSolver.jar N_CITIES [N_ITERS]  # solve a random TSP of N_CITIES cities with N_ITERS iterations
    java -jar jar/MutationRateExperiment.jar        # do the mutation rate experiment
    java -jar jar/CrossoverExperiment.jar           # do the crossover experiment


# Visualization

Run python scripts to draw the results. *Note*: this requires matplotlib lib.

    python scripts/draw_route.py res/last.res

To visualize the experiments result, just run the scripts without any parameters.

    python scripts/draw_mutationrate.py
    # OR
    python scripts/draw_crossover.py
