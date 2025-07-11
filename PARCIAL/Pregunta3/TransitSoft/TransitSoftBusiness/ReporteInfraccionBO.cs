using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using TransitSoftDomain;

namespace TransitSoftBusiness
{
    internal class ReporteInfraccionBO
    {
        public BindingList<ReporteInfraccion> cargarBin()
        {
            BindingList<ReporteInfraccion> lista = new BindingList<ReporteInfraccion>();
            FileStream fs = new FileStream("reportes_infracciones.bin", FileMode.Open);
            BinaryFormatter formatter = new BinaryFormatter();
            try
            {
                while (fs.Position < fs.Length)
                {
                    ReporteInfraccion objeto = (ReporteInfraccion)formatter.Deserialize(fs);
                    lista.Add(objeto);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al deserializar: " + ex.Message);
            }
            finally
            {
                fs.Close();
            }

            return lista;
        }

    }
}
