package com.example.wecare;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MeetDevelopers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_developers);

        ImageButton backButton = findViewById(R.id.backbtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i;
//                i = new Intent(MeetDevelopers.this,MainActivity.class );
//                startActivity(i);
                finish();
            }
        });
        //solomon Sitotaw
        {
            ImageButton githubButton = findViewById(R.id.Sgit);
            githubButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/solomonsitot";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton = findViewById(R.id.Sgmail);
            gmailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"solotest922@gmail.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton = findViewById(R.id.Slin);
            linkedinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://linkedin.com/in/solomon-sitotaw-227161289";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

        }
        //Yordanos Tibebu
        {
            ImageButton githubButton2 = findViewById(R.id.Ytgit);
            githubButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/Yordaaa";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton2 = findViewById(R.id.Ytgmail);
            gmailButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"Yordanostibebu5@gmail.com"});
                    startActivity(i);
                }
            });

            ImageButton linkedinButton2 = findViewById(R.id.Ytlin);
            linkedinButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://www.linkedin.com/in/yordanos-tibebu-981bb7290/";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
        //Yakob zeray
        {
            ImageButton githubButton3 = findViewById(R.id.Yzgit);
            githubButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/jacze-dev/jacze-dev";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton3 = findViewById(R.id.Yzgmail);
            gmailButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"yaikobzeray@gmail.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton3 = findViewById(R.id.Yzlin);
            linkedinButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://www.linkedin.com/in/yaikob-zeray";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
        //Biruk getachew
        {
            ImageButton githubButton4 = findViewById(R.id.Bggit);
            githubButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/Barok-Getachew";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton4 = findViewById(R.id.Bggmail);
            gmailButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"bruk_getachew@yahoo.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton4 = findViewById(R.id.Bglin);
            linkedinButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://www.linkedin.com/in/birukgetachew/";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
        //Yoseph Tesfaye
        {
            ImageButton githubButton5 = findViewById(R.id.Yosgit);
            githubButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/Yordaaa";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton5 = findViewById(R.id.Yosgmail);
            gmailButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"solotest922@gmail.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton5 = findViewById(R.id.Yoslin);
            linkedinButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://www.linkedin.com/in/yordanos-tibebu-981bb7290/";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
        //Yordanos Bogale
        {
            ImageButton githubButton6 = findViewById(R.id.Ybgit);
            githubButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/Yordaaa";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton6 = findViewById(R.id.Ybgmail);
            gmailButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"solotest922@gmail.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton6 = findViewById(R.id.Yblin);
            linkedinButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://www.linkedin.com/in/yordanos-tibebu-981bb7290/";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
        //marta t/michael
        {
            ImageButton githubButton6 = findViewById(R.id.mgit);
            githubButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String githubUrl = "https://github.com/shenahh1";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });

            ImageButton gmailButton6 = findViewById(R.id.mgmail);
            gmailButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"marthatesfa9226@gmail.com"});
                    startActivity(intent);
                }
            });

            ImageButton linkedinButton6 = findViewById(R.id.mlin);
            linkedinButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Define the GitHub URL
                    String linkedinUrl = "https://et.linkedin.com/in/martha-tesfamichael-2ab698296";

                    // Create an Intent to open a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));

                    // Start the activity to open the browser
                    startActivity(intent);
                }
            });
        }
    }
}
