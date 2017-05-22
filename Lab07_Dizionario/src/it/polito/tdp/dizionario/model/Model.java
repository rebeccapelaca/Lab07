package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	WordDAO wordDAO = new WordDAO();
	UndirectedGraph<String, DefaultEdge> graph;

	public List<String> createGraph(int numeroLettere) { 
		
		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		for(String vertice : wordDAO.getAllWordsFixedLength(numeroLettere))
			graph.addVertex(vertice);
			
		for(String vertice : graph.vertexSet())
			for(String verticeSimile : wordDAO.getAllSimilarWordsVertex(vertice, graph.vertexSet()))
				graph.addEdge(vertice, verticeSimile);
		
		return wordDAO.getAllWordsFixedLength(numeroLettere);
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		List<String> neighboursList = new ArrayList<String>();

		System.out.println("Model -- TODO");
		
		neighboursList.addAll(Graphs.neighborListOf(graph, parolaInserita));
		
		return neighboursList;
	}

	public String findMaxDegree() {
		
		String risultato = "";
		String verticeMax = "";
		int max = 0;
		
		for(String vertice : graph.vertexSet())
			if(graph.degreeOf(vertice)>max) {
				max = graph.degreeOf(vertice);
				verticeMax = vertice;
			}
			
		risultato += "Grado massimo: \n" + verticeMax + " : " + max + "\n";
		
		return risultato;
	}
	
	public List<String> trovaTuttiVicini(String verticePartenza) {
		
		List<String> visited = new ArrayList<String>();
		List<String> daVisitare = new ArrayList<String>();
		
		/*
		BreadthFirstIterator<String, DefaultEdge> bfv = new BreadthFirstIterator<>(graph, verticePartenza);
		
		while(bfv.hasNext()) {
			visited.add(bfv.next());
		}
		*/
		
		daVisitare.add(verticePartenza);
		
		while(daVisitare.size()>0) { 
			
			for(String vicino : this.displayNeighbours(daVisitare.get(0))) 
				if(!visited.contains(vicino) && !daVisitare.contains(vicino))
					daVisitare.add(vicino);
			
			visited.add(daVisitare.get(0));
			daVisitare.remove(daVisitare.get(0));	
		}
		
		return visited;
	}
}
