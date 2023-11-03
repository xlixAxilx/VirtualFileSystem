A program which supports basic I/O to access a virtual disk drive, which is memory-resident. 
The program will be able to move data from the real file system to the virtual disk and vice versa.
It only work with text files, so the virtual disk can be represented as a list of FileObjects, where each fileObject is composed of a name and a String of data.
File names in the virtual disk must be lower case letters, and the virtual disk does not contain directories, so there can be at most 26 files in the disk.
It supports 4 commands:

import <PATH> <VFS>
export <VFS> <PATH>
ls
exit
