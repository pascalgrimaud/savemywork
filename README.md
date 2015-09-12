# Information

When you work on a document file (example : document.doc), SaveMyWork
will automatically save your file every X minutes. The save file will
be in the same directory as the original file.

This is used to avoid losing all your work when the file crashes or is corrupted...


# Build with maven

### Requirements

* Java 7+
* Maven 3


### Generate the jar

Clone project, go into directory and build with maven :

```
git clone https://github.com/pascalgrimaud/savemywork.git \
&& cd savemywork \
&& mvn clean package
```

The file "SaveMyWork.jar" is generated in the target directory. You can move it where you want.



# Usage

Go into "SaveMyWork.jar" directory and type :
```
java -jar SaveMyWork.jar <time in minutes> <full path of file1>
```

You can add more files :
```
java -jar SaveMyWork.jar <time in minutes> <file1> [file2] [...]
```
