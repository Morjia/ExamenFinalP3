using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface InfraccionesDAO
    {
        int Insertar(InfraccionesDTO infraccion);
        InfraccionesDTO ObtenerPorId(int infraccionId);
        BindingList<InfraccionesDTO> ListarTodos();
        int Modificar(InfraccionesDTO infraccion);
        int Eliminar(InfraccionesDTO infraccion);
    }
}
