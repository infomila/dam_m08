package net.iesmila.asynctaskapp;


import android.os.AsyncTask;

public class DescarregaAsyncTask extends AsyncTask<Integer,Integer,String> {
    private String resultat = "";

    private IPublicaResultatsDescarrega mActivity;

    private boolean mPareuLesMaquines = false;

    public DescarregaAsyncTask(IPublicaResultatsDescarrega pActivity) {
        mActivity = pActivity;
    }

    @Override
    protected String doInBackground(Integer... numeros) {

        for(int i=0;i<numeros.length ;i++) {
            if(mPareuLesMaquines) break;
            // simulem que fem molta feina
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            resultat += " Usuari "+ numeros[i] + " té " + (int)Math.random()*10 +
                    " peticions";
            publishProgress(i, numeros.length);
        }
        return resultat;
    }

    @Override
    protected void onPostExecute(String resultat) {
        super.onPostExecute(resultat);
        mActivity.publicaResultatDescarrega(resultat);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mActivity.publicaProgressDescarrega(values[0]+1, values[1]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mPareuLesMaquines = true;
    }
}
