package homework2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	// TODO: add additional white box tests



	public static void main(){

		Collection<Object []> tests_list = getFiles();
		for(Object [] test : tests_list){
			GraphTests inst = new GraphTests((java.nio.file.Path) test[0]);
			try {
				inst.testWithScriptFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



	}
}
