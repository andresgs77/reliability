package storageEvaluation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalcEvaluationPAPER {
	
	private ArrayList<Evaluation> evaluations;
	private ArrayList<LoadedValue> load;
	
	public CalcEvaluationPAPER(ArrayList<LoadedValue> load){
		this.load=load;
		evaluations= new ArrayList<Evaluation>();
	}
	
	/*public ArrayList<Evaluation> getCalculations(){
		
		//Calendar today=Calendar.getInstance();
		int dayIndex=0;
		Calendar auxCal=load.get(dayIndex).getDate();
		Calendar today=load.get(dayIndex).getDate();
		today.add(Calendar.YEAR,1);
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
	}*/
	
	public ArrayList<Evaluation> getCalculations(){
		
		
		Calendar c=load.get(0).getDate();
		Calendar cAux= new GregorianCalendar();
		cAux.set(Calendar.YEAR, c.get(Calendar.YEAR));
		cAux.set(Calendar.MONTH, c.get(Calendar.MONTH));
		cAux.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		//ojo a los días!! 271!!!
		for (int i=0; i<365; i++){
			//Calendar c=auxCal.getInstance();
			if (i!=0) cAux.add(Calendar.DAY_OF_MONTH, 1);
			Evaluation e=new Evaluation();
			e.setDate(cAux);
			evaluations.add(e);
		}
		
		
		// eliminar evaluations
		int index=0;
		int mira=0;
		for (Evaluation e: evaluations){
			if(mira<load.size()){
				if (sameDate(e.getDate(),load.get(mira).getDate())){
					index=mira;
					mira++;
				}
			}
			e.setCompleteness(load.get(index).getCompleteness());
			e.setEvalClass(load.get(index).getEvalClass());
			e.setRules(load.get(index).getRules());
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
				auxAcum = 0;
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
			//System.out.println(evaluations.get(i).getReliability());
			
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
		//System.out.println(auxCal1.getTime());
		//System.out.println(auxCal2.getTime());
		if ((auxCal1.get(Calendar.DAY_OF_MONTH)==auxCal2.get(Calendar.DAY_OF_MONTH))
				&& (auxCal1.get(Calendar.MONTH)==auxCal2.get(Calendar.MONTH))
				&& (auxCal1.get(Calendar.YEAR)==auxCal2.get(Calendar.YEAR)))
			return true;
		else 
			return false;
	}

}
