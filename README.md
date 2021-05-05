# FindFiles
FindFiles is a command line application that helps user find target files. The output prints the absolute path of the located files.

User may include flags in their search command. Supported flags include:
<br/>
<br/>
-help &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; :: print out a help page and exit the program. <br/>
-r &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; :: execute the command recursively in subfiles. <br/>
-reg &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; :: treat `filetofind` as a regular expression when searching. <br/>
-dir [directory]  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  :: find files starting in the specified directory. <br/>
-ext [ext1,ext2,...]   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; :: find files matching [filetofind] with extensions [ext1, ext2,...]. <br/>

<br/>
<br/>
openjdk version "11.0.8" 2020-07-14 <br/>
macOS 10.14.6 (MacBook Pro 2019)
