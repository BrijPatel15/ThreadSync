1) Download zip from culearn
2) Open Eclipse
3) File -> Open Projects From File System..
4) Click "Archive.." and Import
5) Open the folder(usually the one that does not have a .zip_expanded extension), go to src and open the "(default package)"
6) Open Host, Server, and Client classes and run in that order 
7) If they do not run check that the files are in the project, if they are not ("J" is not filled in) try the other directory

The MeasurementHelper class is the main class of this assignment, it is responsible for starting all the threads

The Agent class is responsible for putting two random ingredients on the Table

The Chef class is responsible for getting ingredients from the Table and if they are ones it does not have
it will eat the ingredients 

The Table class is responsible for the thread synchronization, it has synchronized methods that only allow one
thread to have control of the Table, it also implements conditional synchronization. When someone is putting 
on the Table they must wait for the table to be empty. When someone is eating from the Table they must wait for the
table to be not empty and for it to be ready to eat.