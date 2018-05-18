JFLAGS = -g
JC = javac
mySort2GB = sbatch mysort2GB.slurm
mySort20GB = sbatch mysort20GB.slurm
linSort2GB = sbatch linsort2GB.slurm
linSort20GB = sbatch linsort20GB.slurm


.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
mySort:
	$(JC) *.java
	$(mySort2GB)
	$(mySort20GB)
	
linSort:
	$(linSort2GB)
	$(linSort20GB)

CLASSES = \
        mySort.java 


clean:
	$(RM) *.class



