from fileinput import filename
import sys
from os.path import dirname, join
from  com.chaquo.python import Python




def plot(codigo):


    file_dir = str(Python.getPlatform().getApplication().getFilesDir())
    filename = join(dirname(file_dir), 'file')
    try:

        original_stout = sys.stdout
        sys.stdout = open(filename, 'w', encoding='utf8', errors="ignore")
        exec(codigo)
        sys.stdout.close()
        sys.stdout = original_stout
        output = open(filename, 'r').read()



    except Exception as e:
        sys.stdout = original_stout
        output = e
    return str(output)

