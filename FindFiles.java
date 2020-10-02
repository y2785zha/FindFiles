import java.util.HashMap;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFiles {

    static void PrintHelp () {
        System.out.println("Usage: java FindFiles filetofind [-option arg]");
        System.out.println("-help                     :: print out a help page and exit the program.");
        System.out.println("-r                        :: execute the command recursively in subfiles.");
        System.out.println("-reg                      :: treat `filetofind` as a regular expression when searching.");
        System.out.println("-dir [directory]          :: find files starting in the specified directory.");
        System.out.println("-ext [ext1,ext2,...]      :: find files matching [filetofind] with extensions [ext1, ext2,...].");
    }

    static boolean CheckFilename(String s) {
        if (s.charAt(0) == '-') {
            System.out.println("Missing file to find, must provide file name before entering options");
            return false;
        } else {
            return true;
        }
    }

    static boolean SearchFile(File dir, final String filename, final boolean recurse, final boolean reg) {
        boolean found = false;
        String dir_name = dir.getName();
        if (reg) {
            System.out.println("Looking for pattern: " + filename.substring(2) + " in directory: " + dir_name);
            for (File f : dir.listFiles()) {
                Pattern p = Pattern.compile(filename);
                Matcher m = p.matcher(f.getName());
                if (m.find()) {
                    System.out.println(f.getAbsolutePath());
                    found = true;
                }
                if (f.isDirectory() && recurse) {
                    found = found || SearchFile(f, filename, recurse, reg);
                }
            }
        } else { // default, look in current
            System.out.println("Looking for file: " + filename + " in directory: " + dir_name);
            for (File f : dir.listFiles()) {
                if (f.getName().equals(filename)) {
                    System.out.println(f.getAbsolutePath());
                    found = true;
                    }
                if (f.isDirectory() && recurse) {
                    found = found || SearchFile(f, filename, recurse, reg);
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        boolean recurse = false;
        boolean reg = false;
        String filename = "";
        HashMap<String, String> Params = new HashMap<String, String>();
        if (args.length < 1) {
            PrintHelp();
            return;
        } else {
            for (int i=0; i < args.length; i++) {
                String s = args[i];
                if (i==0) {
                    if (s.equals("-help")) {
                        PrintHelp();
                        return;
                    } else {
                        filename = s;
                    }
                } else {
                    if (s.equals("-help")) {
                        PrintHelp();
                        return;
                    } else if (s.equals("-r")) {
                        recurse = true;
                    } else if (s.equals("-reg")) {
                        reg = true;
                    } else if (s.equals("-dir")) {
                        if (i == args.length-1) {
                            System.out.println("no dictionary argument provided");
                            return;
                        } else {
                            i++;
                            Params.put("-dir", args[i]);
                        }
                    } else if (s.equals("-ext")) {
                        if (i == args.length-1) {
                            System.out.println("no extension argument provided");
                            return;
                        } else {
                            i++;
                            Params.put("-ext", args[i]);
                        }
                    } else {
                        System.out.println("Invalid option: " + s + " is not a supported option");
                        return;
                    }
                }
                // end of reading parameters
            }
            if(!CheckFilename(filename)) {
                return;
            }

            File dir = new File(System.getProperty("user.dir"));

            if (Params.containsKey("-dir")) {
                File target_dir = new File(Params.get("-dir"));
                if (target_dir.isDirectory()) {
                    dir = target_dir;
                } else {
                    System.out.println("Directory Not Found, please enter a valid absolute or relative path");
                    return;
                }
            }

            if (reg) {
                char[] wildcard = new char[] {'*', '+', '?', '^', '$'};
                boolean escape = false;
                for (char c: wildcard) {
                    if (c == filename.charAt(0)) {
                        escape = true;
                        break;
                    }
                }
                if (escape) {
                    filename = "\\\\" + filename;
                }
            }

            boolean found = SearchFile(dir, filename, recurse, reg);
            if (!found) {
                System.out.print("File: " + filename + " not found in directory: " + dir.getName());
                if (recurse) {
                    System.out.println(" and its sub directories");
                } else {
                    System.out.print("\n");
                }
            }


        }
    }

}
