package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Evento> eventos;
	private ArrayList<DataAgenda> datas;
	
	public Agenda() {
		datas = new ArrayList<DataAgenda>();
		eventos = new ArrayList<Evento>();
	}
	
	public void addData(DataAgenda data) {
		datas.add(data);
		data.setAgenda(this);
	}
	
	public ArrayList<DataAgenda> getDatas() {
		return datas;
	}
	
	public ArrayList<DataAgenda> getDatas(int mes, int ano) {
		
		ArrayList<DataAgenda> datasPesquisa = new ArrayList<DataAgenda>();
				
		for (DataAgenda dataAgenda : datas) {
			LocalDate data = dataAgenda.getDate();
			
			if (data.getMonthValue() == mes && data.getYear() == ano) {
				datasPesquisa.add(dataAgenda);
			}
		}
		return datasPesquisa;
	}
	
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	public void addEvento(Evento evento) {
		eventos.add(evento);
	}
	
	protected boolean removeEvento(Evento evento) {
		return eventos.remove(evento);
	}
	
	public boolean removeDataAgenda(DataAgenda dataAgenda) {
		return datas.remove(dataAgenda);
	}
	
	public ArrayList<Evento> pesquisaEventos() {
		ArrayList<Evento> eventosPesquisa = new ArrayList<Evento>();;
		for (Evento evento : eventos) {
			eventosPesquisa.add(evento);
		}
		eventosPesquisa.sort(null);
		return eventosPesquisa;
	}
	
	public ArrayList<Evento> pesquisaEventos(LocalDate inicio, LocalDate fim) {
		if (inicio.isAfter(fim)) {
			throw new IllegalArgumentException("Intevalo inválido");
		}
		
		ArrayList<Evento> eventosPesquisa = new ArrayList<Evento>();
		
		for (Evento evento : eventos) {
			LocalDate data = evento.getDataHorario().toLocalDate();
			
			if (!(data.isBefore(inicio) || data.isAfter(fim))) {
				eventosPesquisa.add(evento);
			}
		}
		eventosPesquisa.sort(null);
		return eventosPesquisa;
		
	}
	
	public ArrayList<Evento> pesquisaEventos(String nome) {
		ArrayList<Evento> eventosPesquisa = new ArrayList<Evento>();

		for (Evento evento : eventos) {
			if (contem(evento.getNome(), nome)) {
				eventosPesquisa.add(evento);
			}
		}
		eventosPesquisa.sort(null);
		return eventosPesquisa;
		
	}
	
	public ArrayList<Evento> pesquisaEventos(LocalDate inicio, LocalDate fim, String nome) {
		if (inicio.isAfter(fim)) {
			throw new IllegalArgumentException("Intevalo inválido");
		}
		
		ArrayList<Evento> eventosPesquisa = new ArrayList<Evento>();;
		
		for (Evento evento : eventos) {
			LocalDate data = evento.getDataHorario().toLocalDate();
			
			if (!(data.isBefore(inicio) || data.isAfter(fim)) && contem(evento.getNome(), nome)) {
				eventosPesquisa.add(evento);
			}
		}
		
		eventosPesquisa.sort(null);
		return eventosPesquisa;
		
	}
	
	private boolean contem(String a, String b) {

		int q1 = a.length();
		int q2 = b.length();

		if (q1 < q2) {
			return false;
		}

		for (int i = 0; i < q2; i++) {
			
			char charA = a.toUpperCase().charAt(i);
			char charB = b.toUpperCase().charAt(i);

			if (charA != charB) {
				return false;
			}
		}
		return true;
	}
	
	public void salvar() {
		try {	
			FileOutputStream fileOut = new FileOutputStream("Agenda");
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

			objOut.writeObject(this);
			objOut.flush();

			objOut.close();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Agenda carregar() {
		File agendaFile = new File("Agenda");
		if (!agendaFile.exists()) {
			return new Agenda();
		}
		Agenda agenda = null;

		try {
			FileInputStream fileIn = new FileInputStream(agendaFile);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);

			agenda = (Agenda) objIn.readObject();

			objIn.close();
			fileIn.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return agenda;
	}
}
