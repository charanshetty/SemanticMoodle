package model;

import java.util.ArrayList;
import java.util.Iterator;

import java.io.IOException;
import java.sql.SQLException;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.reasoner.rulesys.builtins.Equal;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class DB {
	public static void getCourse(ArrayList<Course> Course)
			throws NullPointerException, SQLException, IOException {
		/************************* Course **************************/

		InfModel infmodel = extractowl.getData();
		String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?individual ?value "
				+ "WHERE { ?individual voc:mdl_course.fullname ?value} ";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			Course instance = new Course();
			Literal name = soln.getLiteral("value");

			System.out.println(name.getString());

			instance.setCoursename(name.getString());

			Course.add(instance);
		}
		qe.close();

	}

	public static void getCategory(ArrayList<CourseCategory> Category)
			throws NullPointerException, SQLException, IOException {

		/************************* Category **************************/

		InfModel infmodel1 = extractowl.getData();
		String queryString1 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?individual ?value "
				+ "WHERE {?individual voc:mdl_course_categories.name ?value} ";
		com.hp.hpl.jena.query.Query query1 = QueryFactory.create(queryString1);

		QueryExecution qe1 = QueryExecutionFactory.create(query1, infmodel1);
		com.hp.hpl.jena.query.ResultSet results1 = qe1.execSelect();

		while (results1.hasNext()) {
			CourseCategory instance1 = new CourseCategory();
			QuerySolution soln = results1.nextSolution();
			Literal name = soln.getLiteral("value");

			System.out.println(name.getString());

			instance1.setCategory(name.getString());

			Category.add(instance1);
		}
		qe1.close();

	}

	public static void getRole(ArrayList<Role> Role)
			throws NullPointerException, SQLException, IOException {

		/************************* Role **************************/

		InfModel infmodel2 = extractowl.getData();
		String queryString2 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?individual ?value "
				+ "WHERE {?individual voc:mdl_role.shortname ?value} ";
		com.hp.hpl.jena.query.Query query2 = QueryFactory.create(queryString2);

		QueryExecution qe2 = QueryExecutionFactory.create(query2, infmodel2);
		com.hp.hpl.jena.query.ResultSet results2 = qe2.execSelect();

		while (results2.hasNext()) {
			Role instance2 = new Role();
			QuerySolution soln = results2.nextSolution();
			Literal name = soln.getLiteral("value");
			System.out.println(name.getString() + "\t");

			instance2.setRolename(name.getString());

			Role.add(instance2);
		}
		qe2.close();

	}

	public static void getUser(ArrayList<User> User) throws ClassCastException,
			NullPointerException, SQLException, IOException {

		/************************* USER **************************/

		InfModel infmodel3 = extractowl.getData();
		String queryString3 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?individual ?value "
				+ "WHERE {?individual voc:mdl_user.firstname ?value} ";
		com.hp.hpl.jena.query.Query query3 = QueryFactory.create(queryString3);

		QueryExecution qe3 = QueryExecutionFactory.create(query3, infmodel3);
		com.hp.hpl.jena.query.ResultSet results3 = qe3.execSelect();

		while (results3.hasNext()) {
			User instance3 = new User();
			QuerySolution soln = results3.nextSolution();
			Literal name = soln.getLiteral("value");

			

			instance3.setUsername(name.getString());

			User.add(instance3);
		}
		qe3.close();

	}
	/************************* Properties **************************/
	public static void getProperties(ArrayList<PropertyN> properties,
			String mdlclass) throws NullPointerException, SQLException,
			IOException {

		InfModel infmodel6 = extractowl.getData();
		OntModel forclasses = extractowl.getmodel();
		ArrayList<OntClass> allclasses = new ArrayList<OntClass>();
		allclasses = extractowl.getClasses(forclasses);
		for (OntClass tmpclass : allclasses) {
			if (!tmpclass.toString().equalsIgnoreCase(mdlclass)) {

				String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
						+ "SELECT DISTINCT ?prop "
						+ "WHERE { ?student ?prop ?lecturer."
						+ "?student a voc:"
						+ mdlclass
						+ "."
						+ "?lecturer a voc:"
						+ tmpclass.getLocalName().toString() + "." + "}";
				
				com.hp.hpl.jena.query.Query query = QueryFactory
						.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query,
						infmodel6);
				com.hp.hpl.jena.query.ResultSet results = qe.execSelect();
		
				// ResultSetFormatter.out(System.out, results, query);
				while (results.hasNext()) {
					PropertyN instance = new PropertyN();
					System.out.println(results.toString());
					QuerySolution soln = results.nextSolution();

					Resource value = soln.getResource("prop");
					System.out.println(value);
					if (!value.getLocalName().equals(null)) {
						System.out.println(value.getLocalName());
						instance.setPropertyName(value.getLocalName());

						properties.add(instance);

						System.out.println(properties.size());
					}
				}
				qe.close();
			}
		}

	}
	/************************* listing Properties **************************/
	public static void listProperties(ArrayList<PropertyN> properties
			) throws NullPointerException, SQLException,
			IOException {

		
		OntModel m = extractowl.getmodel();
		//ArrayList<PropertyN> allprops = new ArrayList<PropertyN>();
		Iterator<ObjectProperty> opty = m.listObjectProperties();

		while (opty.hasNext()) {
			ObjectProperty otype = opty.next();
			PropertyN inst=new PropertyN();
		inst.setPropertyName(otype.getLocalName().toString());
		properties.add(inst);
		}
						

						System.out.println(properties.size());
					}
		

	

	/************************* User related predicates **************************/
	public static void getUserProperty(ArrayList<User> User,
			ArrayList<Course> Course, ArrayList<Role> Role,
			ArrayList<CourseCategory> Category, String queryname)
			throws ClassCastException, NullPointerException, SQLException,
			IOException {
		// /////////////////////////////////get role
		// property////////////////////////////////////////////////////////////////////////////
		User u=new User();
		u.setUsername(queryname);
		User.add(u);
		InfModel infmodel = extractowl.getData();
		String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?value ?course  "
				+ "WHERE {?individual voc:mdl_user.firstname ?value . "
				+ "?individual voc:hasrolename   ?indiv2 ."
				+ "?indiv2     voc:mdl_role.shortname ?course ."
				+ "   	FILTER( ?value = "
				+ "\""
				+ queryname
				+ "\""
				+ ")"
				+ "} ";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();
		String x = null;
		// ResultSetFormatter.out(System.out, results, query);
		while (results.hasNext()) {
			Role instance = new Role();
			QuerySolution soln = results.nextSolution();

			Literal value = soln.getLiteral("course");
			x = value.getString();
			System.out.println(value.getString());

			instance.setRolename(value.getString());

			Role.add(instance);
		}
		qe.close();

		// //////////////////////////////////get student's
		// courses/////////////////////////////////////////////////////////////////////////////////
		if ((x != null) & (x.equalsIgnoreCase("student"))) {

			InfModel infmodel1 = extractowl.getData();
			// String name2 = name;
			String queryString1 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
					+ "SELECT ?value ?course  "
					+ "WHERE {?individual voc:mdl_user.firstname ?value . "
					+ "?individual voc:hastakencourse   ?indiv2 ."
					+ "?indiv2     voc:mdl_course.fullname ?course ."
					+ "   	FILTER( ?value = "
					+ "\""
					+ queryname
					+ "\""
					+ ")"
					+ "} ";

			com.hp.hpl.jena.query.Query query1 = QueryFactory
					.create(queryString1);

			QueryExecution qe1 = QueryExecutionFactory
					.create(query1, infmodel1);
			com.hp.hpl.jena.query.ResultSet results1 = qe1.execSelect();

			// ResultSetFormatter.out(System.out, results, query);
			while (results1.hasNext()) {
				Course instance = new Course();
				QuerySolution soln = results1.nextSolution();

				Literal value = soln.getLiteral("course");

				System.out.println(value.getString());

				instance.setCoursename(value.getString());

				Course.add(instance);
			}
			qe1.close();
		}

		// //////////////////////////////////////////////////teacher teaches
		// courses////////////////////////////////////////////////////////////
		else if (x != null) {
			InfModel infmodel2 = extractowl.getData();
			// String name2 = name;
			String queryString2 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
					+ "SELECT ?value ?course  "
					+ "WHERE {?individual voc:mdl_user.firstname ?value . "
					+ "?individual voc:teaches   ?indiv2 ."
					+ "?indiv2     voc:mdl_course.fullname ?course ."
					+ "   	FILTER( ?value = "
					+ "\""
					+ queryname
					+ "\""
					+ ")"
					+ "} ";

			com.hp.hpl.jena.query.Query query2 = QueryFactory
					.create(queryString2);

			QueryExecution qe2 = QueryExecutionFactory
					.create(query2, infmodel2);
			com.hp.hpl.jena.query.ResultSet results2 = qe2.execSelect();

			// ResultSetFormatter.out(System.out, results, query);
			while (results2.hasNext()) {
				Course instance = new Course();
				QuerySolution soln = results2.nextSolution();

				Literal value = soln.getLiteral("course");

				System.out.println(value.getString());

				instance.setCoursename(value.getString());

				Course.add(instance);
			}
			qe2.close();
		}
	}

	/************************* course related predicates **************************/
	public static void getCourseProperty(ArrayList<User> User,
			ArrayList<Course> Course, ArrayList<Role> Role,
			ArrayList<CourseCategory> Category, String queryname)
			throws ClassCastException, NullPointerException, SQLException,
			IOException {
		Course c = new Course();
		c.setCoursename(queryname);
		Course.add(c);
		InfModel infmodel = extractowl.getData();
		// String name1 = name;
		String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> " +
				"PREFIX owl:<http://www.w3.org/2002/07/owl#>" +
		"SELECT ?value  ?course  " +
		"WHERE {" +
		"voc:isenrolledby owl:inverseOf ?reln." +
		"?individual ?reln   ?indiV3 ." +
		"?indiV3 voc:mdl_course.fullname ?course . " +
		"?individual     voc:mdl_user.firstname ?value ." +
		"   	FILTER( ?course = "+"\""+queryname+"\""+")" +
			"} ";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results.hasNext()) {
			User instance = new User();
			QuerySolution soln = results.nextSolution();

			Literal value = soln.getLiteral("value");

			System.out.println(value.getString() + "lookingat");

			instance.setUsername(value.getString());

			User.add(instance);
		}
		qe.close();

		InfModel infmodel1 = extractowl.getData();
		// String name1 = name;

		String queryString1 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?value ?course  "
				+ "WHERE {?individual voc:mdl_course.fullname ?value . "
				+ "?individual voc:belongsto   ?indiv2 ."
				+ "?indiv2     voc:mdl_course_categories.name ?course ."
				+ "   	FILTER( ?value = "
				+ "\""
				+ queryname
				+ "\""
				+ ")"
				+ "} ";

		com.hp.hpl.jena.query.Query query1 = QueryFactory.create(queryString1);

		QueryExecution qe1 = QueryExecutionFactory.create(query1, infmodel1);
		com.hp.hpl.jena.query.ResultSet results1 = qe1.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results1.hasNext()) {
			CourseCategory instance = new CourseCategory();
			QuerySolution soln = results1.nextSolution();

			Literal value = soln.getLiteral("course");

			System.out.println(value.getString());

			instance.setCategory(value.getString());

			Category.add(instance);
		}
		qe1.close();

	}

	// ///////////////////////////////role related predicates///////////////////////////////////////////////////////////////
	public static void getRoleProperty(ArrayList<User> User,
			ArrayList<Course> Course, ArrayList<Role> Role,
			ArrayList<CourseCategory> Category, String queryname)
			throws ClassCastException, NullPointerException, SQLException,
			IOException {
		Role r= new Role();
		r.setRolename(queryname);
		Role.add(r);
		

		InfModel infmodel = extractowl.getData();
		// String name2 = name;
		String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> " +
				"PREFIX owl:<http://www.w3.org/2002/07/owl#>" +
		"SELECT ?value  ?course  " +
		"WHERE {" +
		"voc:are owl:inverseOf ?reln." +
		"?individual ?reln   ?indiV3 ." +
		"?indiV3 voc:mdl_role.shortname ?course . " +
		"?individual     voc:mdl_user.firstname ?value ." +
		"   	FILTER( ?course = "+"\""+queryname+"\""+")" +
			"} ";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results.hasNext()) {
			User instance = new User();
			QuerySolution soln = results.nextSolution();

			Literal value = soln.getLiteral("value");

			System.out.println(value.getString());

			instance.setUsername(value.getString());

			User.add(instance);
		}
		qe.close();

	}

	public static void getCategoryProperty(ArrayList<User> User,
			ArrayList<Course> Course, ArrayList<Role> Role,
			ArrayList<CourseCategory> Category, String queryname)
			throws ClassCastException, NullPointerException, SQLException,
			IOException {
		
		CourseCategory cc=new CourseCategory();
		cc.setCategory(queryname);
		Category.add(cc);
		
		InfModel infmodel = extractowl.getData();
		// String name1 = name;
		String queryString = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> " +
				"PREFIX owl:<http://www.w3.org/2002/07/owl#>" +
		"SELECT ?value  ?course  " +
		"WHERE {" +
		"voc:hascourses owl:inverseOf ?reln." +
		"?individual ?reln   ?indiV3 ." +
		"?individual voc:mdl_course.fullname ?course . " +
		"?indiV3     voc:mdl_course_categories.name ?value ." +
		"   	FILTER( ?value = "+"\""+queryname+"\""+")" +
			"} ";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, infmodel);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results.hasNext()) {
			Course instance = new Course();
			QuerySolution soln = results.nextSolution();

			Literal value = soln.getLiteral("course");

			System.out.println(value.getString());

			instance.setCoursename(value.getString());

			Course.add(instance);
		}
		qe.close();

		InfModel infmodel1 = extractowl.getData();
		// String name1 = name;

		String queryString1 = "PREFIX voc:<http://biostorm.stanford.edu/db_table_classes/DSN_jdbc.mysql.//localhost.3306/moodle#> "
				+ "SELECT ?value ?course  "
				+ "WHERE {?individual voc:mdl_user.firstname ?course . "
				+ "?indiv2 voc:haspeople   ?individual ."
				+ "?indiv2     voc:mdl_course_categories.name ?value ."
				+ "   	FILTER( ?value = "
				+ "\""
				+ queryname
				+ "\""
				+ ")"
				+ "} ";

		com.hp.hpl.jena.query.Query query1 = QueryFactory.create(queryString1);

		QueryExecution qe1 = QueryExecutionFactory.create(query1, infmodel1);
		com.hp.hpl.jena.query.ResultSet results1 = qe1.execSelect();

		// ResultSetFormatter.out(System.out, results, query);
		while (results1.hasNext()) {
			User instance = new User();
			QuerySolution soln = results1.nextSolution();

			Literal value = soln.getLiteral("course");

			System.out.println(value.getString());

			instance.setUsername(value.getString());

			User.add(instance);
		}
		qe1.close();

	}

}
