seeping
=======

Simple project for visualizing ping results over time. Lets you "see" ping. Get it?

Using
-----
Compile and run the script like so:

    ./run.sh ip_address

Also, you can symlink to the script and use it that way:

    ln -s $(pwd)/run.sh ~/bin/seeping

The first time you run the script, it can take a while to download all the Java
and Scala dependencies.

Example Output
--------------

    10:40 |###############################.############################|#####%%%%%%%%%.| 59
    10:41 |############################################################|#####%%%%%%%%%%| 60
    10:42 |#######.#######...#....................................#....|#..............| 16
    10:43 |#.....#..#..#############.################################.#|####...........| 49
    10:44 |######.###.#################################################|#####%%%%%%%%..| 58
    10:45 |###########################.#####.#####.#######.######.#####|#####%%%%%.....| 55
    10:46 |#.#####.###.#.##..##.##.####..##.#################.#########|####...........| 48
    10:47 |.##############.##.############.######.#.#####.#######.#####|#####%%........| 52
    10:48 |#########################.###...............................|##.............| 28
    10:49 |......................................######################|##.............| 22
    10:50 |############################################################|#####%%%%%%%%%%| 60
    10:51 |############################################################|#####%%%%%%%%%%| 60
    10:52 |############################################################|#####%%%%%%%%%%| 60
    10:53 |############################################################|#####%%%%%%%%%%| 60

The columns are as follows:

* The time.
* The outcome of each ping. "#" is success and "." is failure.
* A scaled histogram. Individual ping detail is only provided for 50-60. < 50 is grouped by tens.
* The total successful pings (out of 60).

If the day rolls over midnight, the new date is output on a single line.
