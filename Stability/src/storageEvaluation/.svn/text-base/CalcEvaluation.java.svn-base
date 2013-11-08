package storageEvaluation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalcEvaluation {
	
	private ArrayList<Evaluation> evaluations;
	private ArrayList<LoadedValue> load;
	
	public CalcEvaluation(ArrayList<LoadedValue> load){
		this.load=load;
		evaluations= new ArrayList<Evaluation>();
	}
	
	public ArrayList<Evaluation> getCalculations(){
		
		Calendar today=Calendar.getInstance();
		int dayIndex=0;
		Calendar auxCal=load.get(dayIndex).getDate();
		boolean ended=false;
		boolean nextFound=false;
		boolean lastOnes=false;
		while (!ended){
			if(!lastOnes)
			addNewEvaluation(load.get(dayIndex));
			if(dayIndex+1<load.size()){
				Calendar auxCal2=load.get(dayIndex+1).getDate();
				Calendar auxCal1=auxCal;
				nextFound=false;
				while (!nextFound){
					auxCal1.add(Calendar.DAY_OF_MONTH, 1);
					if (!auxCal1.getTime().after(auxCal2.getTime()) && !sameDate(auxCal1,auxCal2))
						addCopiedEvaluation(load.get(dayIndex),auxCal1);
					else
						{
						nextFound=true;
						dayIndex++;
						}
				}
			}
			else{
				lastOnes=true;
				Calendar auxCal1=auxCal;
				auxCal1.add(Calendar.DAY_OF_MONTH, 1);
				if (!auxCal1.getTime().after(today.getTime()) && !sameDate(auxCal1,today)){
					addCopiedEvaluation(load.get(dayIndex),auxCal1);
				}
				else ended=true;	
			}
		}
		calcReliability();
		return evaluations;
	}
	
	private void calcReliability(){
		double auxAcum=0;
		double auxAcum2=0;
		double auxAvg=0;
		double auxVar=0;
		double dt=0;
		
		for (int i = 0; i < evaluations.size(); i++) {
			if (i == 0)
				auxAcum = evaluations.get(i).getCompleteness();
			else
				auxAcum = auxAcum
						+ (evaluations.get(i).getCompleteness() - evaluations.get(i - 1).getCompleteness());

			auxAvg = auxAcum / (i + 1);

			if (i == 0) {
				auxAcum2 = 0;
				auxVar = auxAcum2;
			} else {
				auxAcum2 = auxAcum2 + Math.pow(evaluations.get(i).getCompleteness()- evaluations.get(i - 1).getCompleteness()- auxAvg, 2);
				auxVar=auxAcum2/(i);
			}
			
			dt=Math.sqrt(auxVar);
			double stability=1-dt;
			evaluations.get(i).setStability(stability);
			evaluations.get(i).setReliability(stability*evaluations.get(i).getCompleteness());
			
		}
	}

	private void addCopiedEvaluation(LoadedValue l, Calendar auxCal1) {
		System.out.println(auxCal1.getTime());
		Calendar c= new GregorianCalendar();
		c.set(Calendar.YEAR, auxCal1.get(Calendar.YEAR));
		c.set(Calendar.MONTH, auxCal1.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, auxCal1.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, auxCal1.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, auxCal1.get(Calendar.MINUTE));
		Evaluation e=new Evaluation();
		e.setDate(c);
		e.setCompleteness(l.getCompleteness());
		e.setEvalClass(l.getEvalClass());
		e.setRules(l.getRules());
		evaluations.add(e);
		
	}

	private void addNewEvaluation(LoadedValue l) {
		System.out.println(l.getDate().getTime());
		Calendar c= new GregorianCalendar();
		c.set(Calendar.YEAR, l.getDate().get(Calendar.YEAR));
		c.set(Calendar.MONTH, l.getDate().get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, l.getDate().get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, l.getDate().get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, l.getDate().get(Calendar.MINUTE));
		Evaluation e=new Evaluation();
		e.setDate(c);
		e.setCompleteness(l.getCompleteness());
		e.setEvalClass(l.getEvalClass());
		e.setRules(l.getRules());
		evaluations.add(e);
	}

	private boolean sameDate(Calendar auxCal1, Calendar auxCal2) {
		if ((auxCal1.get(Calendar.DAY_OF_MONTH)==auxCal2.get(Calendar.DAY_OF_MONTH))
				&& (auxCal1.get(Calendar.MONTH)==auxCal2.get(Calendar.MONTH))
				&& (auxCal1.get(Calendar.YEAR)==auxCal2.get(Calendar.YEAR)))
			return true;
		else 
			return false;
	}

}
