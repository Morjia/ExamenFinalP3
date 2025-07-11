using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class VehiculosDTO
    {
        private int? vehiculoId;
        private string placa;
        private string marca;
        private string modelo;
        private int? anho;

        public VehiculosDTO()
        {
            this.VehiculoId = null;
            this.Placa = null;
            this.Marca = null;
            this.Modelo = null;
            this.Anho = null;
        }

        public VehiculosDTO(int vehiculoId, string placa, string marca, string modelo, int anho)
        {
            this.VehiculoId = vehiculoId;
            this.Placa = placa;
            this.Marca = marca;
            this.Modelo = modelo;
            this.Anho = anho;
        }

        public int? VehiculoId { get => vehiculoId; set => vehiculoId = value; }
        public string Placa { get => placa; set => placa = value; }
        public string Marca { get => marca; set => marca = value; }
        public string Modelo { get => modelo; set => modelo = value; }
        public int? Anho { get => anho; set => anho = value; }
    }
}
