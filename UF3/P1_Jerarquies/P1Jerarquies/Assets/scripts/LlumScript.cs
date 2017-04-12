using UnityEngine;
using System.Collections;
using System;

public class LlumScript : MonoBehaviour {

    public float VelocitatGir = 1;
    public float VelocitatAvans = 1;

    public float AlfaMax;
    public float BetaMax;


    public GameObject Llum;
    public GameObject rotulaBase;

    private bool mHeXocat = false;
    public float VELOCITAT_SALT = 0.01f;
    private float vel=0;
    private float g = 0;

    // Use this for initialization
    void Start () {
        rotulaBase = this.transform.Find("S1").gameObject;
    }
	
	// Update is called once per frame
	void Update () {

        if(Input.GetButton("Jump"))
        {
            vel = VELOCITAT_SALT;
            // this.transform.Rotate(new Vector3(0, VelocitatGir, 0));
            g = -1f;
        }

        this.transform.position += new Vector3(0, vel*Time.deltaTime, 0);
        vel += g;

        float h = Input.GetAxis("Horizontal");
        this.transform.RotateAround(transform.position, Vector3.up, h*VelocitatGir);


        // h és +1 si anem a la dreta, i -1 si anem a l'esquerra.
        bool baixa = Input.GetKey(KeyCode.F);
        bool puja = Input.GetKey(KeyCode.G);
        int girBras = 0;
        if (baixa) girBras = +1;
        else if(puja) girBras = -1;

        if (girBras != 0) {
            float variacioAngle = girBras * VelocitatGir;

            if (girBras < 0 || girBras > 0 && !mHeXocat) {
                rotulaBase.transform.Rotate(new Vector3(0, 0, variacioAngle));
            }
            if(girBras < 0)
            {
                mHeXocat = false;
            }
            /*
             * 
             * Versió amb limitació angular
            

            float angleZ = this.transform.localRotation.eulerAngles.z;
            Debug.Log(angleZ);
            if (angleZ > 180) angleZ = angleZ - 360;

            if (angleZ + variacioAngle > -20 && angleZ + variacioAngle < 20)
            {
                this.transform.Rotate(new Vector3(0, 0, variacioAngle));
                    
            }*/
           // Debug.Log(angleZ);           

        }

        if (Llum!=null && Input.GetKeyUp(KeyCode.L))
        {
            Light li = Llum.GetComponent<Light>();
            li.enabled = !li.enabled;
        }

        float v = Input.GetAxis("Vertical");
        //this.transform.position = new Vector3(
        //                            transform.position.x + VelocitatAvans * v, 
        //                            transform.position.y, 
        //                            transform.position.z);

        //this.transform.position += new Vector3(
        //                            VelocitatAvans * v,
        //                            0,
        //                            0);

        // projectem el vector en el pla
        Vector3 direccioAvans = new Vector3(this.transform.right.x, 0, this.transform.right.z);
        // assegurem que tingui longitud 1
        direccioAvans.Normalize();
        this.transform.position += (-v*VelocitatAvans * direccioAvans);

        Debug.DrawRay(this.transform.position, this.transform.right,Color.red);
        Debug.DrawRay(this.transform.position, direccioAvans, Color.green);
        //
        // Debug.Log("HOLA MON");

    }
    
    internal void setXoc(bool v)
    {
        mHeXocat = v;
    }
}
