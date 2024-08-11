#  clear
#  javac Graph.java Main.java -Xlint:unchecked
#  java Main
javac -d dist $(find * -name "*.java")
java -cp dist Main