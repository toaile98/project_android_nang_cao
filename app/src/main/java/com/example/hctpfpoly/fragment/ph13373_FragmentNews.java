package com.example.hctpfpoly.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.activity.ph13373_WebViewActivity;
import com.example.hctpfpoly.model.ph13373_XML_Parse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ph13373_FragmentNews extends Fragment {

    private ListView lvNews;



    ArrayAdapter<String> adapter;
    List<String> arrTittle=new ArrayList<>();
    List<String> arrLink=new ArrayList<>();
    Intent intent;
    public ph13373_FragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ph13373__news, container, false);
        lvNews = (ListView) view.findViewById(R.id.lv_news);
        new RSSHauTruong().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,arrTittle);
        lvNews.setAdapter(adapter);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link =arrLink.get(position);
                intent = new Intent(getActivity(), ph13373_WebViewActivity.class);
                intent.putExtra("linkWeb",link);
                startActivity(intent);
            }
        });
        return view;
    }

    private class RSSHauTruong extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            //bat dau xu li
            //1-khai bao duong dan doc du lieu
            try {
                URL url =new URL(strings[0]);//lay ve duong dan dau tien
                //ket noi voi server:url.openConnection
                //tao luon doc:inputStreamReader
                InputStreamReader reader
                        =new InputStreamReader(url.openConnection().getInputStream());
                //tao bo dem
                BufferedReader bufferedReader=new BufferedReader(reader);
                //doc theo dong
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);//dua dong doc duoc vao content
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();

        }

        @Override
        protected void onPostExecute(String content) {
            super.onPostExecute(content);

            ph13373_XML_Parse xmlParser=new ph13373_XML_Parse();

            try {
                //tao 1 tai lieu tu content
                Document document =xmlParser.getDocument(content);
                //lay ve tat ca cac node item
                NodeList nodeList =document.getElementsByTagName("item");
                //lay ve link ,title
                String link="",title="";
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element)nodeList.item(i);
                    title=xmlParser.getValue(element,"title")+"\n";//lay ve title
                    arrTittle.add(title);//lay title dua vao list
                    arrLink.add(xmlParser.getValue(element,"link"));//lay link dua vao list
                }
                adapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }
}