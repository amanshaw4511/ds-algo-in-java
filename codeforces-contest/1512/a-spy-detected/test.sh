#!/bin/bash

# syntax 
# ./test.sh input_file output_file

OPTION=$1
INPUT_FILE=$2
OUTPUT_FILE=$3

[ ! -f $INPUT_FILE ] && echo $INPUT_FILE not found && exit 1
[ ! -f $OUTPUT_FILE ] && echo $OUTPUT_FILE not found && exit 1

 
test_this() {
    echo 'input :'
    cat $INPUT_FILE

    echo 'expected output :'
    cat $OUTPUT_FILE

    echo 'your output :'
    java Solution.java < $INPUT_FILE
    
}

test_all() {
    for ((i=1; i<9; i++)) {
        INPUT_FILE="input$i"
        OUTPUT_FILE="output$i"

        if [ -f $INPUT_FILE -a -f $OUTPUT_FILE ] 
        then
            echo -e "--------------$INPUT_FILE : $OUTPUT_FILE----------------"
            test_this
        fi
    }

}


case "$OPTION" in
    "-t") test_this ;;
    "-a") test_all ;;
    *) echo wrong Syntax ;;
esac

