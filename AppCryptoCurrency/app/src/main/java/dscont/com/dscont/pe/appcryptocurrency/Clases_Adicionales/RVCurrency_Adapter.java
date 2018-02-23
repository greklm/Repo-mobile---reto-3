package dscont.com.dscont.pe.appcryptocurrency.Clases_Adicionales;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dscont.com.dscont.pe.appcryptocurrency.R;

/**
 * Created by Carla-PC on 21/02/2018.
 */

public class RVCurrency_Adapter  extends RecyclerView.Adapter<RVCurrency_Adapter.CurrencyViewHolder>{

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        CardView cv_Currency;
        TextView tv_Nombre;
        TextView tv_Price;
        TextView tv_Numero;
        TextView tv_PercentChange24h;
        TextView tv_PercentChange7d;

        ImageView iv_Photo;

        CurrencyViewHolder(View itemView) {
            super(itemView);
            cv_Currency = (CardView)itemView.findViewById(R.id.cv_Currency);
            tv_Nombre = (TextView)itemView.findViewById(R.id.tv_Nombre);
            tv_Price = (TextView)itemView.findViewById(R.id.tv_Price);
            tv_Numero = (TextView)itemView.findViewById(R.id.tv_Numero);
            tv_PercentChange24h = (TextView)itemView.findViewById(R.id.tv_PercentChange24h);
            tv_PercentChange7d = (TextView) itemView.findViewById(R.id.tv_PercentChange7d);
            iv_Photo = (ImageView)itemView.findViewById(R.id.iv_Photo);
        }
    }

    List<cCurrency> Monedas;
    Activity Actividad;
    public RVCurrency_Adapter(List<cCurrency> monedas, Activity actividad){
        this.Monedas = monedas;
        this.Actividad = actividad;
    }
    @Override
    public int getItemCount() {
        return Monedas.size();
    }
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_row, viewGroup, false);
        CurrencyViewHolder pvh = new CurrencyViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(CurrencyViewHolder currencyViewHolder, int i) {
        currencyViewHolder.tv_Nombre.setText(Monedas.get(i).getName()+"|"+Monedas.get(i).getSymbol());
        currencyViewHolder.tv_Price.setText(String.valueOf("$ "+Monedas.get(i).getPriceUsd()));
        currencyViewHolder.tv_Numero.setText(String.valueOf(Monedas.get(i).getRank()));
        double PercentChange24h = Double.parseDouble(Monedas.get(i).getPercentChange24h());
        currencyViewHolder.tv_PercentChange24h.setText(Monedas.get(i).getPercentChange24h());
        //Asignar el color al TextView según el valor del contenido
        if(PercentChange24h>0)
        {
            currencyViewHolder.tv_PercentChange24h.setTextColor(Color.parseColor("#2E64FE"));
        }else
        {
            currencyViewHolder.tv_PercentChange24h.setTextColor(Color.parseColor("#FE2E2E"));
        }

        double PercentChange7d = Double.parseDouble(Monedas.get(i).getPercentChange7d());
        if(PercentChange7d>0)
        {
            currencyViewHolder.tv_PercentChange7d.setTextColor(Color.parseColor("#2E64FE"));
        }else
        {
            currencyViewHolder.tv_PercentChange7d.setTextColor(Color.parseColor("#FE2E2E"));
        }
        currencyViewHolder.tv_PercentChange7d.setText(Monedas.get(i).getPercentChange7d());

        String Cod_Moneda = Monedas.get(i).getSymbol();
        //Se carga la imagen de la moneda con Picasso
        Picasso.with(currencyViewHolder.iv_Photo.getContext())
                .load("https://github.com/cjdowner/cryptocurrency-icons/blob/master/128/color/"+Cod_Moneda.toLowerCase()+".png?raw=true")
                .into(currencyViewHolder.iv_Photo);
        //currencyViewHolder.iv_Photo.setImageResource(R.mipmap.ic_launcher);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
