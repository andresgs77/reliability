package storageEvaluation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalcEvaluationATOMFull {
	
	private ArrayList<Evaluation> evaluations;
	private ArrayList<LoadedValue> load;
	
	public CalcEvaluationATOMFull(ArrayList<LoadedValue> load){
		this.load=load;
		evaluations= new ArrayList<Evaluation>();
	}
	
	public ArrayList<Evaluation> getCalculations(/*Calendar fromDate, Calendar toDate*/){
		
		Calendar c=load.get(0).getDate();
		Calendar cfin=Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		cfin.set(Calendar.HOUR, 0);
		cfin.set(Calendar.MINUTE, 0);
		cfin.set(Calendar.SECOND, 0);
		//System.out.println(c.getTime().toString() + " to " + cfin.getTime().toString());
		long numDaysMilis= cfin.getTime().getTime() - c.getTime().getTime(); //in milis
		long numDays = numDaysMilis / (24 * 60 * 60 * 1000);
		Calendar cAux= new GregorianCalendar();
		cAux.set(Calendar.YEAR, c.get(Calendar.YEAR));
		cAux.set(Calendar.MONTH, c.get(Calendar.MONTH));
		cAux.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		for (int i=0; i<numDays; i++){
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
		
		
		//shortenEvaluations(fromDate, toDate);
		return evaluations;
	}
	
	private void shortenEvaluations(Calendar fromDate, Calendar toDate) {
		ArrayList<Evaluation> aux= new ArrayList<Evaluation>();
		
		double lastIntro=-1.0;
		for (int i=0; i<evaluations.size(); i++){
			Evaluation e=evaluations.get(i);
			//System.out.println(e.getDate().getTime().toString());
			//si es lo primero que entra
			if (sameDate(e.getDate(),fromDate)){
				lastIntro=e.getCompleteness();
				//comprobamos si es distinto al valor anterior en evaluations
				if (i>0 && (e.getCompleteness()!=evaluations.get(i-1).getCompleteness())){
				aux.add(e);
				//o si es el primer valor de evaluations
				} else if (i==0){
					aux.add(e);
				}
				//si es un día intermedio o es el último día
			}else if ((middleDate(e.getDate(),fromDate,toDate))|| sameDate(e.getDate(),toDate)){
				if (e.getCompleteness()!=lastIntro){
					aux.add(e);
					lastIntro=e.getCompleteness();
				}
			}
		}
		evaluations=aux;
		//for (Evaluation e: evaluations)System.out.println(e.getDate().getTime().toString());
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
	
	private boolean middleDate(Calendar aux, Calendar begin, Calendar end) {
		if (aux.compareTo(begin)>0 && aux.compareTo(end)<0)
			return true;
		return false;
	}
	


}
